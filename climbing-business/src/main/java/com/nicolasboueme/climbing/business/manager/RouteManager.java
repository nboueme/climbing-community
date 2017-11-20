package com.nicolasboueme.climbing.business.manager;

import com.nicolasboueme.climbing.consumer.dao.DaoFactory;
import com.nicolasboueme.climbing.consumer.dao.RouteDao;

public class RouteManager {
    private RouteDao routeDao;

    public void initDao() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        routeDao = daoFactory.getRouteDao();
    }

    public RouteDao getRouteDao() {
        return routeDao;
    }
}
