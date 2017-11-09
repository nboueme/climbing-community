package com.nicolasboueme.climbing.consumer.dao;

import com.nicolasboueme.climbing.model.beans.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDaoImplementation implements RouteDao {
    private DaoFactory daoFactory;

    RouteDaoImplementation(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Route> listRoutesFromParent(int sectorId) {
        List<Route> routeList = new ArrayList<Route>();
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet result;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT publication.name, route.height, route.points_number, route.quotation, route.publication_id FROM publication, route WHERE publication.id = route.publication_id AND route.sector_id = ?;");
            preparedStatement.setInt(1, sectorId);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                String name = result.getString("name");
                int height = result.getInt("height");
                int pointsNumber = result.getInt("points_number");
                String quotation = result.getString("quotation");
                int publicationId = result.getInt("publication_id");
                Route route = new Route();
                route.setName(name);
                route.setHeight(height);
                route.setPoints_number(pointsNumber);
                route.setQuotation(quotation);
                route.setPublication_id(publicationId);
                routeList.add(route);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return routeList;
    }

    public Route getRoute(int publicationId) {
        Route route = new Route();
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet result;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT publication.name, route.height, route.points_number, route.quotation FROM publication, route WHERE publication.id = route.publication_id AND route.publication_id = ?;");
            preparedStatement.setInt(1, publicationId);
            result = preparedStatement.executeQuery();

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
