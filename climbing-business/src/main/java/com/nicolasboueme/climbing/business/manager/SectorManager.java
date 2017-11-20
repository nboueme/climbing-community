package com.nicolasboueme.climbing.business.manager;

import com.nicolasboueme.climbing.consumer.dao.DaoFactory;
import com.nicolasboueme.climbing.consumer.dao.SectorDao;

public class SectorManager {
    private SectorDao sectorDao;

    public void initDao() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        sectorDao = daoFactory.getSectorDao();
    }

    public SectorDao getSectorDao() {
        return sectorDao;
    }
}
