package com.fardaz.rm.user;

import com.fardaz.rm.web.BasePage;
import com.fardaz.rm.web.RemoteWebService;
import com.fardaz.rm.web.ServiceLocator;
import com.google.common.base.Strings;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;

/**
 * @author Mamad
 * @version 1.0
 * @since 20/10/12
 */
public class MyRemoteWebPage extends BasePage {

    public MyRemoteWebPage(PageParameters params) {
        super(params);
        WebMarkupContainer updateBar = newYouTubePanel("updateBar", false);
        add(updateBar);
    }

    public static WebMarkupContainer newYouTubePanel(String id, final boolean forceUpdate) {
        WebMarkupContainer updateBar = new WebMarkupContainer(id);
        updateBar.setOutputMarkupId(true);
        updateBar.add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(5)) {

            @Override
            protected void onPostProcessTarget(AjaxRequestTarget target) {
                RemoteWebService remoteWebService = ServiceLocator.getInstance().getRemoteWebService();
                String account = "me";
                if (forceUpdate || remoteWebService.needToUpdate(account)) {
                    String videoId = remoteWebService.getYouTubeVideoId(account);
                    target.appendJavaScript("updateVideo('" + videoId + "');");
                    remoteWebService.updated(account);
                }
            }
        });

        return updateBar;
    }

    @Override
    protected String getPageTitle(PageParameters params) {
        return "Remote Web";
    }
}
