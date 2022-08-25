package com.norcane.noble.web.faces.view;

import com.norcane.noble.web.faces.NobleFacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component("applicationV")
@ApplicationScope
public class ApplicationView extends FacesView {

    @Autowired
    public ApplicationView(NobleFacesContext nobleFacesContext) {
        super(nobleFacesContext);
    }

    @Value("${noble.vendor.copyright}")
    private String copyright;

    public String getCopyright() {
        return copyright;
    }
}
