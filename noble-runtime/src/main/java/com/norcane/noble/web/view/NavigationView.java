package com.norcane.noble.web.view;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component("navigationV")
@ApplicationScope
public class NavigationView extends FacesView {

    public boolean isCurrentView(String outcome) {
        return currentViewId().equals(outcome);
    }

    public boolean hasActiveSubmenu(String outcome) {
        final String viewIdPrefix = stripSubmenu(currentViewId());
        final String outcomePrefix = stripSubmenu(outcome);

        return viewIdPrefix.equals(outcomePrefix);
    }

    private String stripSubmenu(String path) {
        return path.substring(0, path.lastIndexOf("/"));
    }
}
