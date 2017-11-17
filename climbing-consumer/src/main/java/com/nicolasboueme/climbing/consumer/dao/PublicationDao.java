package com.nicolasboueme.climbing.consumer.dao;

import com.nicolasboueme.climbing.model.entity.Comment;
import com.nicolasboueme.climbing.model.entity.Publication;

import java.util.List;

public interface PublicationDao {
    List<Publication>  listPublication();
    List<Comment> getCommentsFromPublication();
}
