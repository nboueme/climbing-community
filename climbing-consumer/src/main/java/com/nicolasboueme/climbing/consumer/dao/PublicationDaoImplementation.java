package com.nicolasboueme.climbing.consumer.dao;

import com.nicolasboueme.climbing.model.beans.Comment;
import com.nicolasboueme.climbing.model.beans.Publication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationDaoImplementation implements PublicationDao {
    private DaoFactory daoFactory;

    PublicationDaoImplementation(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Publication> listPublication() {
        /*List<Publication> publicationList = new ArrayList<Publication>();
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet result;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT publication.name FROM publication WHERE publication.name LIKE ?;");
            preparedStatement.setString(1, "%c%");
            result = preparedStatement.executeQuery();

            while (result.next()) {
                String name = result.getString("name");
                Publication publication = new Publication();
                publication.setName(name);
                publicationList.add(publication);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return publicationList;*/

        return null;
    }

    public List<Comment> getCommentsFromPublication() {
        return null;
    }
}
