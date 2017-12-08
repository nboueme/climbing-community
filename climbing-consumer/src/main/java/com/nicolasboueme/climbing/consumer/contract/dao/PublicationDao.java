package com.nicolasboueme.climbing.consumer.contract.dao;

import com.nicolasboueme.climbing.model.entity.Comment;
import com.nicolasboueme.climbing.model.entity.Publication;

import java.util.List;

public interface PublicationDao {
    List<Publication>  listPublication();

    void addComment(Comment comment);

    List<Comment> getParentsComments(Comment comment);

    List<Comment> getChildrenComments(Comment comment);

    void updateComment(Comment comment);

    void deleteComment(Comment comment);
}
