package com.nicolasboueme.climbing.business.impl.manager;

import com.nicolasboueme.climbing.business.contract.manager.PublicationManager;
import com.nicolasboueme.climbing.business.impl.AbstractManager;
import com.nicolasboueme.climbing.model.entity.*;

import java.util.List;

public class PublicationManagerImpl extends AbstractManager implements PublicationManager {

    public List<Spot> listPublication(Spot spot) {
        return getDaoFactory().getPublicationDao().listPublication(spot);
    }

    public List<Sector> listPublication(Sector sector) {
        return getDaoFactory().getPublicationDao().listPublication(sector);
    }

    public List<Route> listPublication(Route route) {
        return getDaoFactory().getPublicationDao().listPublication(route);
    }

    public List<Topo> listPublication(Topo topo) {
        return getDaoFactory().getPublicationDao().listPublication(topo);
    }

    public void addComment(Comment comment) {
        getDaoFactory().getPublicationDao().addComment(comment);
    }

    public List<Comment> getParentsComments(Comment comment) {
        return getDaoFactory().getPublicationDao().getParentsComments(comment);
    }

    public List<Comment> getChildrenComments(Comment comment) {
        return getDaoFactory().getPublicationDao().getChildrenComments(comment);
    }

    public void updateComment(Comment comment) {
        getDaoFactory().getPublicationDao().updateComment(comment);
    }

    public void deleteComment(Comment comment) {
        getDaoFactory().getPublicationDao().deleteComment(comment);
    }
}
