package com.nicolasboueme.climbing.consumer.dao;

import com.nicolasboueme.climbing.model.beans.Comment;
import com.nicolasboueme.climbing.model.beans.Publication;

import java.util.List;

public interface PublicationDao {
    List<Publication>  listPublication();
    List<Comment> getCommentsFromPublication();
}
