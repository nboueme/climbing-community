package com.nicolasboueme.climbing.consumer.dao;

import com.nicolasboueme.climbing.model.entity.Topo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopoDaoImplementation implements TopoDao {
    private DaoFactory daoFactory;

    TopoDaoImplementation(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Topo> listTopo() {
        List<Topo> topoList = new ArrayList<Topo>();
        Connection connection;
        Statement statement;
        ResultSet result;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT publication.name, topo.description, topo.publication_id FROM publication, topo WHERE publication.id = topo.publication_id;");

            while (result.next()) {
                String name = result.getString("name");
                String description = result.getString("description");
                int publicationId = result.getInt("publication_id");
                Topo topo = new Topo();
                topo.setName(name);
                topo.setDescription(description);
                topo.setPublicationId(publicationId);
                topoList.add(topo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topoList;
    }

    public Topo getTopo(int topoId) {
        Topo topo = new Topo();
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet result;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT publication.name, topo.description FROM publication, topo WHERE publication.id = topo.publication_id AND topo.publication_id = ?;");
            preparedStatement.setInt(1, topoId);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                String name = result.getString("name");
                String description = result.getString("description");
                topo.setName(name);
                topo.setDescription(description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topo;
    }
}
