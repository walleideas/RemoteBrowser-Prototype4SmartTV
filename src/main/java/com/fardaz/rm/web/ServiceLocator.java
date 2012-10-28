package com.fardaz.rm.web;

/**
 * User: Mamad
 * Date: 20/07/12
 * Time: 22:49
 */
public class ServiceLocator {

    //todo: move this to another class, in a separate java process
    private static ServiceLocator instance;
    private RemoteWebService remoteWebService;

    private ServiceLocator() {
    }

    public static ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
            try {
                instance.init();
            } catch (Exception e) {
                //
            }
        }

        return instance;
    }


    public void init() throws Exception {
        remoteWebService = new RemoteWebServiceImpl();
    }

    public RemoteWebService getRemoteWebService() {
        return remoteWebService;
    }

}
