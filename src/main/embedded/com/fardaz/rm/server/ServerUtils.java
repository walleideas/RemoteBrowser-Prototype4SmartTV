/*
 * Copyright (c) 2011, Your Corporation. All Rights Reserved.
 */

package com.fardaz.rm.server;


import java.text.NumberFormat;

public class ServerUtils {
    public static final int DEFAULT_LISTEN_PORT = 80;
    public static final int DEFAULT_SSL_PORT = 443;

    public static final long ONE_HOUR_MILLISECONDS = 1000l * 60 * 60;

    public static final String DOMAIN_URL = getStringSystemProperty("deploy.host",
            "deploy.host is not set, set it to localhost:" + DEFAULT_LISTEN_PORT,
            "http://localhost:" + DEFAULT_LISTEN_PORT);

    public static NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance();
    public static final int FEEDS_COUNT = getIntSystemProperty("feeds.count", "feeds.count is not set, set it to 20", 20);

    /**
     * Search in system properties for <b>propertyName</b> and try to convert it to integer. Return defaultValue on any error
     *
     * @param propertyName the property to search in system properties
     * @param errorMessage the error message displayed to user
     * @param defaultValue default value for property name
     * @return integer value of system property with name propertyName
     */
    public static int getIntSystemProperty(String propertyName, String errorMessage, int defaultValue) {
        try {
            int intValue = Integer.parseInt(System.getProperty(propertyName, String.valueOf(defaultValue)));
            System.out.println("Setting '" + propertyName + "' to:" + intValue);
            return intValue;
        } catch (Exception e) {
            System.err.println(errorMessage);
            return defaultValue;
        }
    }

    public static boolean getBoolSystemProperty(String propertyName, String errorMessage, boolean defaultValue) {
        try {
            return Boolean.parseBoolean(System.getProperty(propertyName, String.valueOf(defaultValue)));
        } catch (Exception e) {
            System.err.println(errorMessage);
            return defaultValue;
        }
    }

    public static String getStringSystemProperty(String propertyName, String errorMessage, String defaultValue) {
        try {
            String value = System.getProperty(propertyName, String.valueOf(defaultValue));
            System.out.println("Setting '" + propertyName + "' to:" + value);
            return value;
        } catch (Exception e) {
            System.err.println(errorMessage);
            return defaultValue;
        }
    }

}