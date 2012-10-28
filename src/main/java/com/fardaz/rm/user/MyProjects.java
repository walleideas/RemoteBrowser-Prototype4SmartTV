package com.fardaz.rm.user;

import com.fardaz.rm.web.BasePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * @author Mamad
 * @version 1.0
 * @since 07/10/12
 */
public class MyProjects extends BasePage {

    public MyProjects(PageParameters params) {
        super(params);
    }

    @Override
    protected String getPageTitle(PageParameters params) {
        return "Projects";
    }
}
