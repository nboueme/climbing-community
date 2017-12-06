package com.nicolasboueme.climbing.consumer.contract.dao;

import com.nicolasboueme.climbing.model.entity.Route;

import java.util.List;

public interface RouteDao {
    List<Route> listRoutesFromParent(Route route);
    Route getRoute(Route route);
    List<Route> listLengthsFromRoute(Route route);
}
