package com.nicolasboueme.climbing.consumer.contract.dao;

import com.nicolasboueme.climbing.model.entity.*;

import java.util.List;

public interface PublicationDao {
    List<Spot> listPublication(Spot spot);

    List<Sector> listPublication(Sector sector);

    List<Route> listPublication(Route route);

    List<Topo> listPublication(Topo topo);

    void addComment(Comment comment);

    List<Comment> getParentsComments(Comment comment);

    List<Comment> getChildrenComments(Comment comment);

    void updateComment(Comment comment);

    void deleteComment(Comment comment);
}
