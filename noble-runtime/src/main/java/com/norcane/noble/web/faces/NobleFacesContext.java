package com.norcane.noble.web.faces;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import jakarta.faces.context.FacesContext;

@Component
@ApplicationScope
public class NobleFacesContext {

    public String currentViewId() {
        return facesContext().getViewRoot().getViewId().split("\\.")[0];
    }

    public FacesContext facesContext() {
        return FacesContext.getCurrentInstance();
    }
}
