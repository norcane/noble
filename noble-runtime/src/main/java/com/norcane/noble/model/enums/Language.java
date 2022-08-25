package com.norcane.noble.model.enums;

import java.util.Locale;

public enum Language {

    ENGLISH_US("English", Locale.ENGLISH),
    CZECH("Čeština", new Locale("cs", "CZ"));

    private final String label;
    private final Locale locale;

    Language(String label, Locale locale) {
        this.label = label;
        this.locale = locale;
    }

    public static Language defaultLanguage() {
        return ENGLISH_US;
    }


    public String getLabel() {
        return label;
    }

    public Locale getLocale() {
        return locale;
    }
}
