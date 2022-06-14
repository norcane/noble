package com.norcane.noble.web.view;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component("navigationV")
@ApplicationScope
public class NavigationView extends FacesView {

    public boolean isCurrentView(String outcome) {
        return currentViewId().equals(outcome);
    }
}
