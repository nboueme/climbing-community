package com.nicolasboueme.climbing.consumer.impl.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

public abstract class AbstractDaoImpl {

    private static DataSource dataSource;

    public static void setDataSource(DataSource dataSource) {
        AbstractDaoImpl.dataSource = dataSource;
    }

    static JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    static NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
