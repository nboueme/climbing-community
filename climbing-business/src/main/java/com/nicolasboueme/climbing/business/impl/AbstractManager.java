package com.nicolasboueme.climbing.business.impl;

import com.nicolasboueme.climbing.consumer.impl.DaoFactoryImpl;

public abstract class AbstractManager {

    private static DaoFactoryImpl daoFactory;

    protected static DaoFactoryImpl getDaoFactory() {
        return daoFactory;
    }

    public static void setDaoFactory(DaoFactoryImpl daoFactory) {
        AbstractManager.daoFactory = daoFactory;
    }
}
