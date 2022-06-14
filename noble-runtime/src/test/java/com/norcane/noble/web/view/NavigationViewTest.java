package com.norcane.noble.web.view;

import com.norcane.noble.web.faces.NobleFacesContext;
import com.norcane.noble.web.faces.view.NavigationView;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NavigationViewTest {

    @Mock
    private NobleFacesContext nobleFacesContext;

    @InjectMocks
    private NavigationView navigationView;

    @Test
    public void testIsCurrentView() {
        final String outcome = "/foo/a";

        // -- mocks
        when(nobleFacesContext.currentViewId()).thenReturn("/foo/a", "/foo/b");

        assertTrue(navigationView.isCurrentView(outcome));
        assertFalse(navigationView.isCurrentView(outcome));

        // -- verify
        verify(nobleFacesContext, times(2)).currentViewId();
    }
}
