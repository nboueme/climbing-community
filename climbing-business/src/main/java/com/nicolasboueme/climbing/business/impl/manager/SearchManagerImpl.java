package com.nicolasboueme.climbing.business.impl.manager;

import com.nicolasboueme.climbing.business.contract.manager.SearchManager;
import com.nicolasboueme.climbing.business.impl.AbstractManager;
import com.nicolasboueme.climbing.consumer.impl.DaoFactoryImpl;
import com.nicolasboueme.climbing.consumer.contract.dao.PublicationDao;
import com.nicolasboueme.climbing.consumer.impl.dao.AbstractDaoImpl;
import com.nicolasboueme.climbing.model.entity.Comment;
import com.nicolasboueme.climbing.model.entity.Publication;

import java.util.List;

public class SearchManagerImpl extends AbstractManager implements SearchManager {

    public List<Publication> listPublication() {
        return getDaoFactory().getPublicationDao().listPublication();
    }

    public List<Comment> getCommentsFromPublication() {
        return getDaoFactory().getPublicationDao().getCommentsFromPublication();
    }
}
