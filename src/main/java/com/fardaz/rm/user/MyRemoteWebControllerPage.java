package com.fardaz.rm.user;

import com.fardaz.rm.web.BasePage;
import com.fardaz.rm.web.ServiceLocator;
import com.google.common.base.Strings;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mamad
 * @version 1.0
 * @since 20/10/12
 */
public class MyRemoteWebControllerPage extends BasePage{
    private boolean videoUpdated;

    public MyRemoteWebControllerPage(PageParameters params) {
        super(params);

        add(new FeedbackPanel("feedback"));
        MyRemoteWebControllerBean bean = new MyRemoteWebControllerBean();
        IModel<MyRemoteWebControllerBean> model = new CompoundPropertyModel<MyRemoteWebControllerBean>(bean);
        Form<MyRemoteWebControllerBean> form = new Form<MyRemoteWebControllerBean>("form", model) {
            @Override
            protected void onSubmit() {
                String videoId = getModelObject().update();
                info("Watch new video on your TV");
            }
        };
        add(form);

        form.add(new TextField<String>("youtubeUrl"));

        add(MyRemoteWebPage.newYouTubePanel("updateBar", isVideoUpdated()));
    }

    @Override
    protected String getPageTitle(PageParameters params) {
        return "Remote Web Controller";
    }

    public boolean isVideoUpdated() {
        if (!videoUpdated) {
            videoUpdated = true;
            return true;
        }
        return false;
    }

    static class MyRemoteWebControllerBean implements Serializable {
        String youtubeUrl;
        public static final Pattern VIDEO_ID_PATTERN = Pattern.compile("(?<=videos\\/|v=)([\\w-]+)");

        public String update() {
            //extract video id and update remote web service
            if (!Strings.isNullOrEmpty(youtubeUrl)) {

                Matcher matcher = VIDEO_ID_PATTERN.matcher(youtubeUrl);
                if (matcher.find()) {
                    String videoId=matcher.group();
                    ServiceLocator.getInstance().getRemoteWebService().setYouTubeVideoId("me", videoId);
                    return videoId;
                }
            }

            return null;
        }
    }
}
