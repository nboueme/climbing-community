package com.nicolasboueme.climbing.consumer.dao;

import com.nicolasboueme.climbing.model.entity.Route;

import java.util.List;

public interface RouteDao {
    List<Route> listRoutesFromParent(int sectorId);
    Route getRoute(int publicationId);
}
