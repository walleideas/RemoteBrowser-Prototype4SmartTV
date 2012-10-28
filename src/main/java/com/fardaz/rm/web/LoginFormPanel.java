package com.fardaz.rm.web;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import javax.security.auth.login.LoginException;
import java.io.Serializable;

/**
 * @author Mamad
 * @version 1.0
 * @since 13/08/12
 */
public class LoginFormPanel extends Panel {
    public LoginFormPanel(String id) {
        super(id);

        IModel<LoginBean> model = new CompoundPropertyModel<LoginBean>(new LoginBean());
        Form<LoginBean> form = new Form<LoginBean>("form", model) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit() {
                try {
                    getModelObject().tryLogin();
                    continueToOriginalDestination();
                    setResponsePage(WebApp.get().getHomePage());
                } catch (LoginException e) {
                    error("Error:" + e.getMessage());
                }
            }

            @Override
            protected void onError() {
                //error("Unknown error");
            }
        };

        form.add(new RequiredTextField<String>("username"));
        form.add(new PasswordTextField("password"));
        form.add(new FeedbackPanel("feedback"));
        add(form);

    }

    private static class LoginBean implements Serializable {
        private static final long serialVersionUID = 1L;
        private String username, password;

        private LoginBean() {
        }

        public void tryLogin() throws LoginException {
            if (!MySession.get().authenticate(username, password)) {
                throw new LoginException("Invalid username or password");
            }
        }
    }
}
