package com.nicolasboueme.climbing.business;

import com.nicolasboueme.climbing.consumer.dao.DaoFactory;
import com.nicolasboueme.climbing.consumer.dao.SpotDao;

public class SpotBusiness {
    private SpotDao spotDao;

    public void initDao() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        spotDao = daoFactory.getSpotDao();
    }

    public SpotDao getSpotDao() {
        return spotDao;
    }
}
