package com.nicolasboueme.climbing.consumer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private String url;
    private String username;
    private String password;

    private DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {

        }

        return new DaoFactory(
                "jdbc:postgresql://localhost:5432/app_climbing",
                "admin_climbing",
                "Shangri_La");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public TopoDao getTopoDao() {
        return new TopoDaoImplementation(this);
    }

    public SpotDao getSpotDao() {
        return new SpotDaoImplementation(this);
    }

    public SectorDao getSectorDao() {
        return new SectorDaoImplementation(this);
    }

    public RouteDao getRouteDao() {
        return new RouteDaoImplementation(this);
    }

    public PublicationDao getPublicationDao() {
        return new PublicationDaoImplementation(this);
    }
}
