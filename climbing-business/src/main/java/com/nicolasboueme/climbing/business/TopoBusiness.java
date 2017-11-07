package com.nicolasboueme.climbing.business;

import com.nicolasboueme.climbing.consumer.dao.DaoFactory;
import com.nicolasboueme.climbing.consumer.dao.TopoDao;

public class TopoBusiness {
    private TopoDao topoDao;

    public void initDao() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        topoDao = daoFactory.getTopoDao();
    }

    public TopoDao getTopoDao() {
        return topoDao;
    }
}
