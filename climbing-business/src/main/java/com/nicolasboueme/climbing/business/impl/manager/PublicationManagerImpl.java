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

    public List<Comment> getParentsComments(int publicationId) {
        return getDaoFactory().getPublicationDao().getParentsComments(publicationId);
    }

    public List<Comment> getChildrenComments(int publicationId) {
        return getDaoFactory().getPublicationDao().getChildrenComments(publicationId);
    }
}
