package com.norcane.noble.web.faces.view;

import com.norcane.noble.web.faces.NobleFacesContext;

import jakarta.faces.application.FacesMessage;

public abstract class FacesView {

    protected final NobleFacesContext nobleFacesContext;

    public FacesView(NobleFacesContext nobleFacesContext) {
        this.nobleFacesContext = nobleFacesContext;
    }

    protected void addFacesMessage(FacesMessage message) {
        nobleFacesContext.facesContext().addMessage(null, message);
    }
}
