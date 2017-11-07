package com.nicolasboueme.climbing.consumer.dao;

import com.nicolasboueme.climbing.model.beans.Spot;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SpotDaoImplementation implements SpotDao {
    private DaoFactory daoFactory;

    SpotDaoImplementation(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Spot> listSpot() {
        List<Spot> spotList = new ArrayList<Spot>();
        Connection connection;
        Statement statement;
        ResultSet result;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT publication.name, spot.height FROM publication, spot WHERE publication.id = spot.publication_id;");

            while (result.next()) {
                String name = result.getString("name");
                int height = result.getInt("height");
                Spot spot = new Spot();
                spot.setName(name);
                spot.setHeight(height);
                spotList.add(spot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return spotList;
    }

    public Spot getSpot() {
        Spot spot = new Spot();
        Connection connection;
        Statement statement;
        ResultSet result;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT publication.name, spot.description, spot.height FROM publication, spot WHERE publication.id = spot.publication_id AND spot.publication_id = 2;");

            while (result.next()) {
                String name = result.getString("name");
                String description = result.getString("description");
                int height = result.getInt("height");
                spot.setName(name);
                spot.setHeight(height);
                spot.setDescription(description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return spot;
    }
}
