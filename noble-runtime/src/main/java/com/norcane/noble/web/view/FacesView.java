package com.norcane.noble.web.view;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public abstract class FacesView {

    public void addFacesMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
