package com.nicolasboueme.climbing.business.contract.manager;

import com.nicolasboueme.climbing.model.entity.Route;

import java.util.List;

public interface RouteManager {
    List<Route> listRoutesFromParent(int sectorId);

    Route getRoute(int routeId);
}
