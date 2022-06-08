package com.norcane.noble.web.view;

import com.norcane.noble.model.enums.Language;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ResourceBundle;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

@Component("localeV")
@SessionScope
public class LocaleView {

    private static final String MESSAGE_BUNDLE_NAME = "locale.messages";

    private Language selectedLanguage;
    private ResourceBundle resourceBundle;

    @PostConstruct
    public void postConstruct() {
        selectedLanguage = Language.defaultLanguage();
        resourceBundle = loadResourceBundle();

    }

    public FacesMessage localizedMessage(FacesMessage.Severity severity, String key) {
        return new FacesMessage(severity, null, resourceBundle.getString(key));
    }

    private ResourceBundle loadResourceBundle() {
        return ResourceBundle.getBundle(MESSAGE_BUNDLE_NAME, selectedLanguage.getLocale());
    }

    public Language getSelectedLanguage() {
        return selectedLanguage;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
}
