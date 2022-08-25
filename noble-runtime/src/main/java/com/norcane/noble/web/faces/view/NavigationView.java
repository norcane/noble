package com.norcane.noble.web.faces.view;

import com.norcane.noble.web.faces.NobleFacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component("navigationV")
@ApplicationScope
public class NavigationView extends FacesView {

    @Autowired
    public NavigationView(NobleFacesContext nobleFacesContext) {
        super(nobleFacesContext);
    }

    public boolean isCurrentView(String outcome) {
        return nobleFacesContext.currentViewId().equals(outcome);
    }

    public boolean hasActiveSubmenu(String outcome) {
        final String viewIdPrefix = stripSubmenu(nobleFacesContext.currentViewId());
        final String outcomePrefix = stripSubmenu(outcome);

        return viewIdPrefix.equals(outcomePrefix);
    }

    private String stripSubmenu(String path) {
        return path.substring(0, path.lastIndexOf("/"));
    }
}
