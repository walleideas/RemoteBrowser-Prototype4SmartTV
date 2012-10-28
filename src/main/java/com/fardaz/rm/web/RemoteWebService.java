package com.fardaz.rm.web;

/**
 * @author Mamad
 * @version 1.0
 * @since 20/10/12
 */
public interface RemoteWebService {
    void setYouTubeVideoId(String account, String videoId);
    String getYouTubeVideoId(String account);

    boolean needToUpdate(String account);

    void updated(String account);
}
