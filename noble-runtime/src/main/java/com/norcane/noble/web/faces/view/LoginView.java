/*
 * noble :: norcane blog engine
 * Copyright (c) 2022 norcane
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.norcane.noble.web.faces.view;

import com.norcane.noble.web.faces.NobleFacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import jakarta.faces.application.FacesMessage;

@Component("loginV")
@RequestScope
public class LoginView extends FacesView {

    private static final String ACTION_LOGIN_FAILURE = "login_failure";
    private static final String ACTION_LOGOUT = "logout";

    private final LocaleView localeView;

    @Autowired
    public LoginView(LocaleView localeView,
                     NobleFacesContext nobleFacesContext) {
        super(nobleFacesContext);
        this.localeView = localeView;
    }

    private String action;

    public void handleAction() {
        if (action == null) {
            return;
        }

        final FacesMessage message = switch (action) {
            case ACTION_LOGIN_FAILURE -> localeView.localizedMessage(FacesMessage.SEVERITY_ERROR, "login.action.failure");
            case ACTION_LOGOUT -> localeView.localizedMessage(FacesMessage.SEVERITY_INFO, "login.action.logout");
            default -> null;
        };

        if (message != null) {
            addFacesMessage(message);
        }
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
