package iu.SpringBoot.Vaadin.logging;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.spring.annotation.SpringComponent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Captures uncaught Vaadin UI errors and writes them to the UI_ACTIONS log.
 */
@SpringComponent
public class VaadinUiErrorHandler implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource()
                .addUIInitListener(
                        uiEvent ->
                                uiEvent.getUI()
                                        .getSession()
                                        .setErrorHandler(
                                                errorEvent -> {
                                                    UI ui = uiEvent.getUI();
                                                    Map<String, Object> details = new HashMap<>();
                                                    details.put("location", getLocation(ui));
                                                    details.put("thread", Thread.currentThread().getName());
                                                    details.put(
                                                            "exception",
                                                            errorEvent
                                                                    .getThrowable()
                                                                    .getClass()
                                                                    .getSimpleName());
                                                    UiActionLogger.logError(
                                                            "vaadin_ui_error",
                                                            errorEvent.getThrowable(),
                                                            details);
                                                    Notification.show(
                                                            "Unexpected error occurred. Please try again or contact admin.",
                                                            4000,
                                                            Notification.Position.MIDDLE);
                                                }));
    }

    private String getLocation(UI ui) {
        if (ui == null) {
            return "unknown";
        }
        return Optional.ofNullable(ui.getInternals().getActiveViewLocation())
                .map(Location::getPath)
                .orElse("unknown");
    }
}
