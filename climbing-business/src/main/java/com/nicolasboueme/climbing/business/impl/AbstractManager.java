package com.nicolasboueme.climbing.business.impl;

import com.nicolasboueme.climbing.consumer.impl.DaoFactoryImpl;

import javax.inject.Inject;

public abstract class AbstractManager {

    @Inject
    private DaoFactoryImpl daoFactory;
    protected DaoFactoryImpl getDaoFactory() {
        return daoFactory;
    }
}
