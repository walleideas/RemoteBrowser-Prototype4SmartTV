package com.fardaz.rm.web;

import org.apache.wicket.authorization.strategies.page.SimplePageAuthorizationStrategy;

/**
 * User: Mamad
 * Date: 01/08/12
 * Time: 19:55
 */
public class AuthStrategy extends SimplePageAuthorizationStrategy {
    public AuthStrategy() {
        super(SecurePage.class, LoginPage.class);
    }

    @Override
    protected boolean isAuthorized() {
        return MySession.isSignedIn();
    }
}
