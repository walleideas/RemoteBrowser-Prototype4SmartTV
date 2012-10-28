/*
 * Copyright (c) 2011, Your Corporation. All Rights Reserved.
 */

package com.fardaz.rm.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.server.ssl.SslSocketConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 */
public class JettyLauncher {
    private static final Logger log = LoggerFactory.getLogger(JettyLauncher.class);

    public static void main(String[] args) {
        JettyLauncher launcher = new JettyLauncher();
        try {
            launcher.startJettyWebServer();
        } catch (Exception e) {
            log.error("Error", e);
        }
    }

    protected void startJettyWebServer() throws Exception{
        //Locale.setDefault(new Locale("fa", "IR"));

        Server server = new Server();
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setMaxIdleTime(1000 * 60 * 60);
        connector.setSoLingerTime(-1);

        int port = ServerUtils.getIntSystemProperty("jetty.port", "Invalid jetty.port, set to default port =" + ServerUtils.DEFAULT_LISTEN_PORT,
                ServerUtils.DEFAULT_LISTEN_PORT);
        connector.setPort(port);

        server.addConnector(connector);
        //SslSocketConnector sslConnector = createSSLConnector();
        //server.addConnector(sslConnector);
        server.setSendDateHeader(true);

        QueuedThreadPool pool = new QueuedThreadPool();
        pool.setMaxThreads(ServerUtils.getIntSystemProperty("maxThreads", "Invalid maxThreads, set default to", 200));
        pool.setMinThreads(ServerUtils.getIntSystemProperty("minThreads", "Invalid minThreads, set default to", 10));
        server.setThreadPool(pool);

        String warDir = System.getProperty("war.dir");
        System.out.println("war.dir = "+ warDir);
        log.debug("war.dir = {}", warDir);
        //System.in.read();
        WebAppContext web = new WebAppContext();
        web.setContextPath("/");
        web.setWar(warDir);
        String webdefaults = warDir + "/WEB-INF/webdefault.xml";
        if (new File(webdefaults).exists()) {
            web.setDefaultsDescriptor(webdefaults);
        } else {
            System.out.println("WebDefaults doesn't exist");
        }

        web.setConfigurationClasses(new String[]{
                "org.eclipse.jetty.webapp.WebInfConfiguration",
                //"org.eclipse.jetty.webapp.EnvConfiguration",
                //"org.eclipse.jetty.webapp.Configuration",
                "org.eclipse.jetty.webapp.WebXmlConfiguration",
                "org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
        });

        //ResourceHandler resourceHandler = new ResourceHandler();
        //resourceHandler.set
        //web.addHandler(resourceHandler);

        server.setSendServerVersion(false);
        server.setHandler(web);
        server.setStopAtShutdown(true);
        try {
            System.out.println("");
            System.out.println("");
            System.out.println(">>> STARTING EMBEDDED JETTY SERVER <<<");
            System.out.println("");
            System.out.println("");
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    protected SslSocketConnector createSSLConnector() {
        String keystorePath = ServerUtils.getStringSystemProperty("jetty.keystore",
                "No jetty.keystore, use keystore  as default (assume in current dir)",
                "keystore");
        SslContextFactory sslContextFactory = new SslContextFactory(keystorePath);

        String keystorePass = ServerUtils.getStringSystemProperty("jetty.keystore.password",
                "No jetty.keystore.password, use *****  as default",
                "mypass");
        sslContextFactory.setKeyStorePassword(keystorePass);

        String keyPassword = ServerUtils.getStringSystemProperty("jetty.key.password",
                "No jetty.key.password, use *****  as default",
                "keypasswd");
        //sslContextFactory.setKeyManagerPassword(keyPassword);


        String trustStore = ServerUtils.getStringSystemProperty("jetty.truststore",
                "No jetty.truststore, use keystore  as default (assume in current dir)",
                "keystore");
        sslContextFactory.setTrustStore(trustStore);

        sslContextFactory.setTrustStorePassword(keystorePass);

        SslSocketConnector connector = new SslSocketConnector(sslContextFactory);
        int sslPort = ServerUtils.getIntSystemProperty("jetty.ssl.port",
                "Invalid jetty.ssl.port, set to default ssl port =" + ServerUtils.DEFAULT_SSL_PORT,
                ServerUtils.DEFAULT_SSL_PORT);
        connector.setPort(sslPort);
        return connector;
    }
}
