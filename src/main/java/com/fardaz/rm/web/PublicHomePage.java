package com.fardaz.rm.web;

import org.apache.wicket.request.mapper.parameter.PageParameters;

public class PublicHomePage extends BasePage {
    private static final long serialVersionUID = 1L;

    public PublicHomePage(final PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected String getPageTitle(PageParameters params) {
        return "Home";
    }

}
