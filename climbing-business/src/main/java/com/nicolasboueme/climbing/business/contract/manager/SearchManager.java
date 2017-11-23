package com.nicolasboueme.climbing.business.contract.manager;

import com.nicolasboueme.climbing.model.entity.Comment;
import com.nicolasboueme.climbing.model.entity.Publication;

import java.util.List;

public interface SearchManager {
    List<Publication> listPublication();

    List<Comment> getCommentsFromPublication();
}
