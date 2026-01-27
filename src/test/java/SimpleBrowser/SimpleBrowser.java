package SimpleBrowser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

/**
 * Simple Swing + JavaFX WebView browser.
 * <p>
 * Notes:
 * - Requires JavaFX modules (javafx-controls, javafx-graphics, javafx-web, javafx-swing).
 * - On JDK 11+, JavaFX is not bundled; add org.openjfx dependencies to build.
 */
public class SimpleBrowser {
    // Default home page
    private static final String HOME_URL = "https://www.google.com";

    // Swing UI
    private JFrame frame;
    private JTextField addressField;
    private JButton backBtn;
    private JButton forwardBtn;
    private JButton reloadBtn;
    private JButton stopBtn;
    private JButton homeBtn;
    private JButton goBtn;
    private JProgressBar progressBar;
    private JLabel statusLabel;
    private JFXPanel jfxPanel;
    private volatile boolean editingAddress = false;

    // JavaFX components
    private WebView webView;
    private WebEngine engine;

    public static void main(String[] args) {
        // Ensure Swing L&F is pleasant
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        EventQueue.invokeLater(
                () -> {
                    SimpleBrowser app = new SimpleBrowser();
                    app.initSwing();
                    app.initJavaFX();
                    app.frame.setVisible(true);
                    // After the frame is visible, focus the address field once
                    SwingUtilities.invokeLater(
                            () -> {
                                app.addressField.requestFocusInWindow();
                                app.addressField.grabFocus();
                                app.addressField.selectAll();
                            });
                });
    }

    private void initSwing() {
        frame = new JFrame("Simple Browser");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1024, 720));

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 6));
        backBtn = new JButton("←");
        forwardBtn = new JButton("→");
        reloadBtn = new JButton("⟳");
        stopBtn = new JButton("✕");
        homeBtn = new JButton("Home");
        addressField = new JTextField(60);
        // Ensure visible size and caret visibility
        addressField.setPreferredSize(new Dimension(600, addressField.getPreferredSize().height));
        addressField.setEditable(true);
        addressField.setEnabled(true);
        addressField.setOpaque(true);
        addressField.setCaretColor(Color.BLACK);
        addressField.setFocusable(true);
        addressField.setRequestFocusEnabled(true);
        addressField.setText(HOME_URL);
        goBtn = new JButton("Go");

        topBar.add(backBtn);
        topBar.add(forwardBtn);
        topBar.add(reloadBtn);
        topBar.add(stopBtn);
        topBar.add(homeBtn);
        topBar.add(new JLabel("URL:"));
        topBar.add(addressField);
        topBar.add(goBtn);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        statusLabel = new JLabel("Ready");

        JPanel bottomBar = new JPanel(new BorderLayout(8, 4));
        bottomBar.add(statusLabel, BorderLayout.WEST);
        bottomBar.add(progressBar, BorderLayout.CENTER);

        jfxPanel = new JFXPanel(); // Initializes JavaFX runtime when first created
        jfxPanel.setFocusable(true); // allow focus when user clicks the web area

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(topBar, BorderLayout.NORTH);
        frame.getContentPane().add(jfxPanel, BorderLayout.CENTER);
        frame.getContentPane().add(bottomBar, BorderLayout.SOUTH);
        frame.pack();

        // Wire Swing actions
        backBtn.addActionListener(e -> fxLater(this::goBack));
        forwardBtn.addActionListener(e -> fxLater(this::goForward));
        reloadBtn.addActionListener(e -> fxLater(this::reload));
        stopBtn.addActionListener(e -> fxLater(this::stop));
        homeBtn.addActionListener(e -> loadUrlOnFX(HOME_URL));
        goBtn.addActionListener(this::onGo);
        addressField.addActionListener(this::onGo);

        // Keep focus stable while typing address (no WebView disable)
        addressField.addFocusListener(
                new java.awt.event.FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent e) {
                        editingAddress = true;
                        addressField.selectAll();
                    }

                    @Override
                    public void focusLost(java.awt.event.FocusEvent e) {
                        editingAddress = false;
                    }
                });

        // Shortcut: Ctrl+L to focus address bar, Ctrl+R reload
        registerShortcut(addressField, "focus-address", KeyEvent.VK_L, true, false, false, e -> addressField.requestFocusInWindow());
        registerShortcut(addressField, "reload", KeyEvent.VK_R, true, false, false, e -> fxLater(this::reload));
    }

    private void initJavaFX() {
        Platform.setImplicitExit(false);
        Platform.runLater(
                () -> {
                    webView = new WebView();
                    engine = webView.getEngine();

                    // Keep Swing UI in sync with load worker
                    engine
                            .getLoadWorker()
                            .stateProperty()
                            .addListener(
                                    (obs, oldState, newState) -> {
                                        if (newState == Worker.State.SUCCEEDED) {
                                            SwingUtilities.invokeLater(
                                                    () -> {
                                                        String title = engine.getTitle();
                                                        String url = engine.getLocation();
                                                        frame.setTitle((title != null ? title : "Simple Browser") + " - " + url);
                                                        addressField.setText(url);
                                                        statusLabel.setText("Done");
                                                    });
                                        } else if (newState == Worker.State.RUNNING) {
                                            SwingUtilities.invokeLater(() -> statusLabel.setText("Loading..."));
                                        } else if (newState == Worker.State.CANCELLED) {
                                            SwingUtilities.invokeLater(() -> statusLabel.setText("Cancelled"));
                                        } else if (newState == Worker.State.FAILED) {
                                            SwingUtilities.invokeLater(() -> statusLabel.setText("Failed"));
                                        }
                                    });

                    engine
                            .getLoadWorker()
                            .progressProperty()
                            .addListener(
                                    new ChangeListener<Number>() {
                                        @Override
                                        public void changed(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) {
                                            int pct = (int) Math.round(newVal.doubleValue() * 100.0);
                                            SwingUtilities.invokeLater(
                                                    () -> {
                                                        progressBar.setIndeterminate(Double.isNaN(newVal.doubleValue()));
                                                        if (!Double.isNaN(newVal.doubleValue())) {
                                                            progressBar.setValue(Math.max(0, Math.min(100, pct)));
                                                            progressBar.setString(pct + "%");
                                                        } else {
                                                            progressBar.setString("...");
                                                        }
                                                    });
                                        }
                                    });

                    // Update title changes too
                    engine
                            .titleProperty()
                            .addListener(
                                    (obs, o, n) -> SwingUtilities.invokeLater(() -> frame.setTitle((n != null ? n : "Simple Browser") + " - " + engine.getLocation())));

                    // Keep address bar in sync with location immediately upon navigation
                    engine
                            .locationProperty()
                            .addListener((obs, oldLoc, newLoc) -> SwingUtilities.invokeLater(() -> addressField.setText(newLoc)));

                    // Allow JavaFX WebView to take focus when user clicks it
                    webView.setFocusTraversable(true);
                    // Show FX scene in Swing
                    jfxPanel.setScene(new Scene(webView));

                    // Initial page
                    safeLoad(HOME_URL);
                });
        // Prefer focusing the address bar initially (one shot)
        SwingUtilities.invokeLater(() -> addressField.requestFocusInWindow());

        // When the user clicks the web area, move focus so typing works in pages/forms
        jfxPanel.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    @Override
                    public void mousePressed(java.awt.event.MouseEvent e) {
                        jfxPanel.requestFocusInWindow();
                        fxLater(() -> {
                            if (webView != null) webView.requestFocus();
                        });
                    }
                });
    }

    private void onGo(ActionEvent e) {
        String raw = addressField.getText().trim();
        if (raw.isEmpty()) return;
        loadUrlOnFX(raw);
        editingAddress = false;
    }

    // Run a small unit of work on JavaFX thread
    private void fxLater(Runnable r) {
        Platform.runLater(r);
    }

    private void goBack() {
        if (engine == null) return;
        WebHistory history = engine.getHistory();
        if (history.getCurrentIndex() > 0) {
            history.go(-1);
        }
    }

    private void goForward() {
        if (engine == null) return;
        WebHistory history = engine.getHistory();
        if (history.getCurrentIndex() + 1 < history.getEntries().size()) {
            history.go(1);
        }
    }

    private void reload() {
        if (engine == null) return;
        engine.reload();
    }

    private void stop() {
        if (engine == null) return;
        engine.getLoadWorker().cancel();
    }

    private void loadUrlOnFX(String input) {
        fxLater(() -> safeLoad(input));
    }

    private void safeLoad(String input) {
        if (engine == null) return;
        try {
            String normalized = normalizeUrl(input);
            engine.load(normalized);
        } catch (Exception ex) {
            SwingUtilities.invokeLater(
                    () ->
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "URLを開けませんでした:\n" + ex.getMessage(),
                                    "Load Error",
                                    JOptionPane.ERROR_MESSAGE));
        }
    }

    private static String normalizeUrl(String input) throws URISyntaxException, MalformedURLException {
        // If looks like a bare word, treat as search query via Google
        if (!input.contains(" ") && (input.startsWith("http://") || input.startsWith("https://"))) {
            // Already absolute
            new URL(input); // validate
            return input;
        }

        // Try to detect host without scheme, e.g., example.com/path
        if (!input.contains(" ") && input.matches("^[a-zA-Z0-9.-]+(\\:[0-9]+)?(/.*)?$")) {
            return "https://" + input;
        }

        // Otherwise treat as a search
        String q = input.trim().replace(" ", "+");
        return "https://www.google.com/search?q=" + q;
    }

    private static void registerShortcut(
            JComponent comp,
            String name,
            int keyCode,
            boolean ctrl,
            boolean alt,
            boolean shift,
            java.awt.event.ActionListener action) {
        int mask = 0;
        if (ctrl) mask |= java.awt.event.InputEvent.CTRL_DOWN_MASK;
        if (alt) mask |= java.awt.event.InputEvent.ALT_DOWN_MASK;
        if (shift) mask |= java.awt.event.InputEvent.SHIFT_DOWN_MASK;

        comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(keyCode, mask), name);
        comp.getActionMap()
                .put(
                        name,
                        new javax.swing.AbstractAction() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                action.actionPerformed(e);
                            }
                        });
    }
}
