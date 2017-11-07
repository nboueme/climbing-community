package com.nicolasboueme.climbing.consumer.dao;

import com.nicolasboueme.climbing.model.beans.Sector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SectorDaoImplementation implements SectorDao {
    private DaoFactory daoFactory;

    SectorDaoImplementation(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Sector> listSector() {
        List<Sector> sectorList = new ArrayList<Sector>();
        Connection connection;
        Statement statement;
        ResultSet result;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT publication.name, sector.height FROM publication, sector WHERE publication.id = sector.publication_id AND sector.spot_id = 2;");

            while (result.next()) {
                String name = result.getString("name");
                int height = result.getInt("height");
                Sector sector = new Sector();
                sector.setName(name);
                sector.setHeight(height);
                sectorList.add(sector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sectorList;
    }

    public Sector getSector() {
        Sector sector = new Sector();
        Connection connection;
        Statement statement;
        ResultSet result;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT publication.name, sector.height FROM publication, sector WHERE publication.id = sector.publication_id AND sector.spot_id = 2 AND sector.publication_id = 3;");

            while (result.next()) {
                String name = result.getString("name");
                int height = result.getInt("height");
                sector.setName(name);
                sector.setHeight(height);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sector;
    }
}
