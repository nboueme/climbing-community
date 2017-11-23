package com.nicolasboueme.climbing.consumer.impl.dao;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

public abstract class AbstractDaoImpl {

    @Inject
    @Named("dataSourceClimbing")
    private DataSource dataSource;

    protected DataSource getDataSource() {
        return dataSource;
    }
}
