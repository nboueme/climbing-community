package com.nicolasboueme.climbing.business.impl.manager;

import com.nicolasboueme.climbing.business.contract.manager.PublicationManager;
import com.nicolasboueme.climbing.business.impl.AbstractManager;
import com.nicolasboueme.climbing.model.entity.Comment;
import com.nicolasboueme.climbing.model.entity.Publication;

import java.util.List;

public class PublicationManagerImpl extends AbstractManager implements PublicationManager {

    public List<Publication> listPublication() {
        return getDaoFactory().getPublicationDao().listPublication();
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
