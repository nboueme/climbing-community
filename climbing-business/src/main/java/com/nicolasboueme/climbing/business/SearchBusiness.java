package com.nicolasboueme.climbing.business;

import com.nicolasboueme.climbing.consumer.dao.DaoFactory;
import com.nicolasboueme.climbing.consumer.dao.PublicationDao;

public class SearchBusiness {
    private PublicationDao publicationDao;

    public void initDao() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        publicationDao = daoFactory.getPublicationDao();
    }

    public PublicationDao getPublicationDao() {
        return publicationDao;
    }
}
