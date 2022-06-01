package com.norcane.noble.view;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component("testV")
@ApplicationScope
public class TestView {

    private final String text = "text from bean";

    public String getText() {
        return text;
    }
}
