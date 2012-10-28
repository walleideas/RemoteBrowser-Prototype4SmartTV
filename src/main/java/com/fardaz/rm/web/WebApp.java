package com.fardaz.rm.web;

import com.fardaz.rm.user.MyHomePage;
import com.fardaz.rm.user.MyProjects;
import com.fardaz.rm.user.MyRemoteWebControllerPage;
import com.fardaz.rm.user.MyRemoteWebPage;
import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.settings.BootstrapSettings;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.core.request.mapper.MountedMapper;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.mapper.parameter.UrlPathPageParametersEncoder;

/**
 */
public class WebApp extends WebApplication
{    	
	@Override
	public Class<? extends BasePage> getHomePage()
	{
		return MyRemoteWebPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
        super.init();

        if (getConfigurationType() == RuntimeConfigurationType.DEVELOPMENT) {
            getMarkupSettings().setStripWicketTags(true);
        } else {
            getSecuritySettings().setAuthorizationStrategy(new AuthStrategy());
        }

        ServiceLocator instance = ServiceLocator.getInstance();

        BootstrapSettings settings = new BootstrapSettings();
        settings.minify(true); // use minimized version of all bootstrap references
        Bootstrap.install(this, settings);

        //public pages
        mountMyPage("pub/h", PublicHomePage.class);
        //user pages
        mountMyPage("u/h", MyHomePage.class);
        mountMyPage("u/r", MyRemoteWebPage.class);
        mountMyPage("u/ctrl", MyRemoteWebControllerPage.class);
        mountMyPage("u/p", MyProjects.class);
	}

    private void mountMyPage(String path, Class<? extends Page> pageClass) {
        mount(new MountedMapper(path, pageClass, new UrlPathPageParametersEncoder()));
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new MySession(request);
    }
}
