package com.nicolasboueme.climbing.consumer.dao;

import com.nicolasboueme.climbing.model.beans.Sector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectorDaoImplementation implements SectorDao {
    private DaoFactory daoFactory;

    SectorDaoImplementation(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Sector> listSectorsFromParent(int spotId) {
        List<Sector> sectorList = new ArrayList<Sector>();
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet result;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT publication.name, sector.height, sector.publication_id FROM publication, sector WHERE publication.id = sector.publication_id AND sector.spot_id = ?;");
            preparedStatement.setInt(1, spotId);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                String name = result.getString("name");
                int height = result.getInt("height");
                int publicationId = result.getInt("publication_id");
                Sector sector = new Sector();
                sector.setName(name);
                sector.setHeight(height);
                sector.setPublication_id(publicationId);
                sectorList.add(sector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sectorList;
    }
}
