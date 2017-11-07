package com.nicolasboueme.climbing.consumer.dao;

import com.nicolasboueme.climbing.model.beans.Route;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RouteDaoImplementation implements RouteDao {
    private DaoFactory daoFactory;

    RouteDaoImplementation(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Route> listRoute() {
        List<Route> routeList = new ArrayList<Route>();
        Connection connection;
        Statement statement;
        ResultSet result;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT publication.name, route.height, route.points_number, route.quotation FROM publication, route WHERE publication.id = route.publication_id AND route.sector_id = 3;");

            while (result.next()) {
                String name = result.getString("name");
                int height = result.getInt("height");
                int pointsNumber = result.getInt("points_number");
                String quotation = result.getString("quotation");
                Route route = new Route();
                route.setName(name);
                route.setHeight(height);
                route.setPoints_number(pointsNumber);
                route.setQuotation(quotation);
                routeList.add(route);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return routeList;
    }

    public Route getRoute() {
        Route route = new Route();
        Connection connection;
        Statement statement;
        ResultSet result;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT publication.name, route.height, route.points_number, route.quotation FROM publication, route WHERE publication.id = route.publication_id AND route.sector_id = 3 AND route.publication_id = 6;");

            while (result.next()) {
                String name = result.getString("name");
                int height = result.getInt("height");
                int pointsNumber = result.getInt("points_number");
                String quotation = result.getString("quotation");
                route.setName(name);
                route.setHeight(height);
                route.setPoints_number(pointsNumber);
                route.setQuotation(quotation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return route;
    }
}
