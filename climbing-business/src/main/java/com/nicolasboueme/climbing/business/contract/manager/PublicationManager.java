package com.nicolasboueme.climbing.business.contract.manager;

import com.nicolasboueme.climbing.model.entity.Comment;
import com.nicolasboueme.climbing.model.entity.Publication;

import java.util.List;

public interface PublicationManager {
    List<Publication> listPublication();

    List<Comment> getParentsComments(int publicationId);

    List<Comment> getChildrenComments(int publicationId);
}
