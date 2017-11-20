package com.nicolasboueme.climbing.business.manager;

import com.nicolasboueme.climbing.consumer.dao.DaoFactory;
import com.nicolasboueme.climbing.consumer.dao.PublicationDao;

public class SearchManager {
    private PublicationDao publicationDao;

    public void initDao() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        publicationDao = daoFactory.getPublicationDao();
    }

    public PublicationDao getPublicationDao() {
        return publicationDao;
    }
}
