package com.nicolasboueme.climbing.business.contract.manager;

import com.nicolasboueme.climbing.model.entity.Route;

import java.util.List;

public interface RouteManager {
    List<Route> listRoutesFromParent(Route route);

    void addRoute(Route route);

    Route getRoute(Route route);

    void updateRoute(Route route);

    void deleteRoute(Route route);

    List<Route> listLengthsFromRoute(Route route);
}
