package com.nicolasboueme.climbing.consumer.dao;

import com.nicolasboueme.climbing.model.beans.Topo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            result = statement.executeQuery("SELECT publication.name, topo.description FROM publication, topo WHERE publication.id = topo.publication_id;");

            while (result.next()) {
                String name = result.getString("name");
                String description = result.getString("description");
                Topo topo = new Topo();
                topo.setName(name);
                topo.setDescription(description);
                topoList.add(topo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topoList;
    }

    public Topo getTopo() {
        Topo topo = new Topo();
        Connection connection;
        Statement statement;
        ResultSet result;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT publication.name, topo.description FROM publication, topo WHERE publication.id = topo.publication_id AND topo.publication_id = 1;");

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
