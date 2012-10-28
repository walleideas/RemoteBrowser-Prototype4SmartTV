package com.fardaz.rm.web;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Mamad
 * @version 1.0
 * @since 20/10/12
 */
public class RemoteWebServiceImpl implements RemoteWebService {
    Map<String, String> youTubeIds = Maps.newHashMap();
    Map<String, Boolean> needUpdate = Maps.newHashMap();

    @Override
    public void setYouTubeVideoId(String account, String videoId) {
        Preconditions.checkNotNull(account);

        if (!Strings.isNullOrEmpty(videoId)) {
            if (!videoId.equals(getYouTubeVideoId(account))) {
                needUpdate.put(account, Boolean.TRUE);
            } else {
                needUpdate.put(account, Boolean.FALSE);
            }

            youTubeIds.put(account, videoId);
        }
    }

    @Override
    public String getYouTubeVideoId(String account) {
        return youTubeIds.get(account);
    }

    @Override
    public boolean needToUpdate(String account) {
        return needUpdate.containsKey(account) && needUpdate.get(account);
    }

    @Override
    public void updated(String account) {
        needUpdate.put(account, Boolean.FALSE);
    }
}
