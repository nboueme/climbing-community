package com.nicolasboueme.climbing.webapp.resource;

import com.nicolasboueme.climbing.business.impl.ManagerFactoryImpl;

public abstract class AbstractResource {

    private static ManagerFactoryImpl managerFactory;

    protected static ManagerFactoryImpl getManagerFactory() {
        return managerFactory;
    }

    public static void setManagerFactory(ManagerFactoryImpl managerFactory) {
        AbstractResource.managerFactory = managerFactory;
    }
}
