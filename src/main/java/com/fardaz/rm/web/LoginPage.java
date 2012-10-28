package com.fardaz.rm.web;

import org.apache.wicket.bootstrap.Bootstrap;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * User: Mamad
 * Date: 01/08/12
 * Time: 19:57
 */
//@RequireHttps
public class LoginPage extends WebPage {
    private static final long serialVersionUID = 1L;

    public LoginPage(PageParameters parameters) {
        super(parameters);

        add(new LoginFormPanel("loginPanel"));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        Bootstrap.renderHead(response);
    }

}
