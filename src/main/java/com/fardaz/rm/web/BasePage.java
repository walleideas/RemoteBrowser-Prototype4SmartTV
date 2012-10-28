package com.fardaz.rm.web;

import com.fardaz.rm.user.MyHomePage;
import com.fardaz.rm.user.MyProjects;
import com.fardaz.rm.user.MyRemoteWebControllerPage;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarButton;
import org.apache.wicket.bootstrap.Bootstrap;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import static de.agilecoders.wicket.markup.html.bootstrap.navbar.Navbar.Position.LEFT;

/**
 * User: Mamad
 * Date: 20/07/12
 * Time: 19:49
 */
public abstract class BasePage extends WebPage {
    private static final long serialVersionUID = 1L;
    private static final String BRAND_NAME = "Smart TV Remote Browser";

    public BasePage(PageParameters params) {
        super(params);
        add(new Label("_pageTitle", getPageTitle(params) + " - "+BRAND_NAME));
        Navbar navBar = new Navbar("navBar");
        add(navBar);
        customiseNavBar(navBar);
    }

    protected String getPageTitle(PageParameters params) {
        return "Base";
    }


    protected void customiseNavBar(Navbar navBar) {
        navBar.fluid().brandName(Model.of(BRAND_NAME));
        //addMenuButton("Home", MyHomePage.class, IconType.Home, navBar);
        addMenuButton("Control", MyRemoteWebControllerPage.class, IconType.Cog, navBar);
        //addMenuButton("Projects", MyProjects.class, IconType.Book, navBar);
        //addMenuButton("Public", PublicHomePage.class, IconType.Flag, navBar);
        //navBar.addButton(LEFT, new NavbarButton<PublicHomePage>(RegistrationPage.class, Model.of("Register")));
        //navBar.addButton(LEFT, new NavbarButton<PublicHomePage>(AboutPage.class, Model.of("About")));
        //navBar.addButton(RIGHT, new NavbarButton<UserHomePage>(UserHomePage.class, Model.of("Upload")));
        if (MySession.isSignedIn()) {
            //navBar.add(); //all logout link
        }
    }

    private void addMenuButton(String label, Class<? extends BasePage> pageClass, IconType iconType, Navbar navBar) {
        Icon homeIcon = new Icon("icon", iconType);
        navBar.addButton(LEFT, new NavbarButton<BasePage>(pageClass, Model.of(label)).setIcon(homeIcon));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        Bootstrap.renderHead(response);
    }
}
