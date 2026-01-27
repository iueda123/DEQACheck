package iu.SpringBoot.Vaadin.security;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@PageTitle("Login | DEQACheckAll")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm loginForm = new LoginForm();

    public LoginView() {
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        // Configure login form
        loginForm.setAction("login");

        // Customize login form text (Japanese)
        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form form = i18n.getForm();
        form.setTitle("");
        form.setUsername("Account");
        form.setPassword("Password");
        form.setSubmit("Login");
        form.setForgotPassword("");
        i18n.setForm(form);

        LoginI18n.ErrorMessage errorMessage = i18n.getErrorMessage();
        errorMessage.setTitle("Authentication failed");
        errorMessage.setMessage("Please check your account and password.");
        i18n.setErrorMessage(errorMessage);

        loginForm.setI18n(i18n);
        loginForm.setForgotPasswordButtonVisible(false);

        add(
                new H1("DEQACheckAll"),
                loginForm
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // Show error message if login failed
        if (event.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            loginForm.setError(true);
        }
    }
}
