package com.nicolasboueme.climbing.consumer.dao;

import com.nicolasboueme.climbing.model.entity.Spot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpotDaoImplementation implements SpotDao {
    private DaoFactory daoFactory;

    SpotDaoImplementation(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Spot> listSpots() {
        List<Spot> spotList = new ArrayList<Spot>();
        Connection connection;
        Statement statement;
        ResultSet result;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT publication.name, spot.height, spot.publication_id FROM publication, spot WHERE publication.id = spot.publication_id;");

            while (result.next()) {
                String name = result.getString("name");
                int height = result.getInt("height");
                int publicationId = result.getInt("publication_id");
                Spot spot = new Spot();
                spot.setName(name);
                spot.setHeight(height);
                spot.setPublicationId(publicationId);
                spotList.add(spot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return spotList;
    }
}
