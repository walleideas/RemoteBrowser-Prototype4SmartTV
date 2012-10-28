package com.fardaz.rm.user;

import com.fardaz.rm.web.BasePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Mamad
 * @version 1.0
 * @since 22/09/12
 */
public class MyHomePage extends BasePage {

    public MyHomePage(PageParameters params) {
        super(params);
    }

    @Override
    protected String getPageTitle(PageParameters params) {
        return "Home";
    }
}
