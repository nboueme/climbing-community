package com.nicolasboueme.climbing.business.manager;

import com.nicolasboueme.climbing.consumer.dao.DaoFactory;
import com.nicolasboueme.climbing.consumer.dao.TopoDao;

public class TopoManager {
    private TopoDao topoDao;

    public void initDao() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        topoDao = daoFactory.getTopoDao();
    }

    public TopoDao getTopoDao() {
        return topoDao;
    }
}
