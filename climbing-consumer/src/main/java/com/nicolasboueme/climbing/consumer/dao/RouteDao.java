package com.nicolasboueme.climbing.consumer.dao;

import com.nicolasboueme.climbing.model.beans.Route;

import java.util.List;

public interface RouteDao {
    List<Route> listRoute();
    Route getRoute();
}
