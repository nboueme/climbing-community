package com.nicolasboueme.climbing.business.impl.manager;

import com.nicolasboueme.climbing.business.contract.manager.RouteManager;
import com.nicolasboueme.climbing.business.impl.AbstractManager;
import com.nicolasboueme.climbing.model.entity.Route;

import java.util.List;

public class RouteManagerImpl extends AbstractManager implements RouteManager {

    public List<Route> listRoutesFromParent(Route route) {
        return getDaoFactory().getRouteDao().listRoutesFromParent(route);
    }

    public void addRoute(Route route) {
        getDaoFactory().getRouteDao().addRoute(route);
    }

    public Route getRoute(Route route) {
        return getDaoFactory().getRouteDao().getRoute(route);
    }

    public void updateRoute(Route route) {
        getDaoFactory().getRouteDao().updateRoute(route);
    }

    public void deleteRoute(Route route) {
        getDaoFactory().getRouteDao().deleteRoute(route);
    }

    public List<Route> listLengthsFromRoute(Route route) {
        return getDaoFactory().getRouteDao().listLengthsFromRoute(route);
    }
}
