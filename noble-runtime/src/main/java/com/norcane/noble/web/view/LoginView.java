package com.norcane.noble.web.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import jakarta.faces.application.FacesMessage;

@Component("loginV")
@RequestScope
public class LoginView extends FacesView {

    private static final String ACTION_LOGIN_FAILURE = "login_failure";
    private static final String ACTION_LOGOUT = "logout";

    private final LocaleView localeView;

    @Autowired
    public LoginView(LocaleView localeView) {
        this.localeView = localeView;
    }

    private String action;

    public void handleAction() {
        if (action == null) {
            return;
        }

        final FacesMessage message = switch (action) {
            case ACTION_LOGIN_FAILURE -> localeView.localizedMessage(FacesMessage.SEVERITY_ERROR, "login.action.failure");
            case ACTION_LOGOUT -> localeView.localizedMessage(FacesMessage.SEVERITY_INFO, "login.action.logout");
            default -> null;
        };

        if (message != null) {
            addFacesMessage(message);
        }
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
