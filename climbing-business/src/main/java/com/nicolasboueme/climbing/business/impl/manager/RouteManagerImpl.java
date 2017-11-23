package com.nicolasboueme.climbing.business.impl.manager;

import com.nicolasboueme.climbing.business.contract.manager.RouteManager;
import com.nicolasboueme.climbing.business.impl.AbstractManager;
import com.nicolasboueme.climbing.model.entity.Route;

import java.util.List;

public class RouteManagerImpl extends AbstractManager implements RouteManager {

    public List<Route> listRoutesFromParent(int sectorId) {
        return getDaoFactory().getRouteDao().listRoutesFromParent(sectorId);
    }

    public Route getRoute(int routeId) {
        return getDaoFactory().getRouteDao().getRoute(routeId);
    }
}
