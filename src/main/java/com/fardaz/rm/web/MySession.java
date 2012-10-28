package com.fardaz.rm.web;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

/**
 * User: Mamad
 * Date: 21/07/12
 * Time: 12:24
 */
public class MySession extends WebSession {
    private static final long serialVersionUID = 1L;

    private String accountId = "accountId";
    private boolean signedIn;

    public MySession(Request request) {
        super(request);
    }

    public static MySession get() {
        return (MySession) Session.get();
    }

    public static String accountId() {
        return get().accountId;
    }

    public boolean authenticate(String username, String password) {
        if (!signedIn && username!=null && username.trim().equalsIgnoreCase("a")) {
            signedIn = true;
        }
        return signedIn;
    }

    public static MySession setAccountId(String accountId) {
        MySession mySession = get();
        mySession.accountId = accountId;
        return mySession;
    }

    public static boolean isSignedIn() {
        MySession mySession = get();
        return mySession.signedIn;
    }
}
