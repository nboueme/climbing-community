package com.nicolasboueme.climbing.webapp.resource;

import com.nicolasboueme.climbing.business.ManagerFactory;

public abstract class AbstractResource {

    private static ManagerFactory managerFactory;

    static ManagerFactory getManagerFactory() {
        return managerFactory;
    }

    public static void setManagerFactory(ManagerFactory managerFactory) {
        AbstractResource.managerFactory = managerFactory;
    }
}
