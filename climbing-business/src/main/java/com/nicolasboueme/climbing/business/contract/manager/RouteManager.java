package com.nicolasboueme.climbing.business.contract.manager;

import com.nicolasboueme.climbing.model.entity.Route;

import java.util.List;

public interface RouteManager {
    List<Route> listRoutesFromParent(Route route);

    Route getRoute(Route route);

    List<Route> listLengthsFromRoute(Route route);
}
