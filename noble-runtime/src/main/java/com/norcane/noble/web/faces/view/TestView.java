package com.norcane.noble.web.faces.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component("testV")
@ApplicationScope
public class TestView {

    @Value("${noble.test-variable}")
    private String testVariable;

    public String getText() {
        return "text from bean: " + testVariable;
    }
}
