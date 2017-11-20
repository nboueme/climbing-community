package com.nicolasboueme.climbing.business;

import com.nicolasboueme.climbing.business.manager.*;

public class ManagerFactory {

    public SpotManager getSpotManager() {
        return new SpotManager();
    }

    public SectorManager getSectorManager() {
        return new SectorManager();
    }

    public RouteManager getRouteManager() {
        return new RouteManager();
    }

    public TopoManager getTopoManager() {
        return new TopoManager();
    }

    public SearchManager getSearchManager() {
        return new SearchManager();
    }
}
