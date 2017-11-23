package com.nicolasboueme.climbing.business.impl;

import com.nicolasboueme.climbing.business.contract.ManagerFactory;
import com.nicolasboueme.climbing.business.contract.manager.RouteManager;
import com.nicolasboueme.climbing.business.contract.manager.SearchManager;
import com.nicolasboueme.climbing.business.contract.manager.SectorManager;
import com.nicolasboueme.climbing.business.contract.manager.SpotManager;
import com.nicolasboueme.climbing.business.contract.manager.TopoManager;

public class ManagerFactoryImpl implements ManagerFactory {

    private SpotManager spotManager;
    public SpotManager getSpotManager() {
        return this.spotManager;
    }
    public void setSpotManager(SpotManager spotManager) {
        this.spotManager = spotManager;
    }

    private SectorManager sectorManager;
    public SectorManager getSectorManager() {
        return this.sectorManager;
    }
    public void setSectorManager(SectorManager sectorManager) {
        this.sectorManager = sectorManager;
    }

    private RouteManager routeManager;
    public RouteManager getRouteManager() {
        return this.routeManager;
    }
    public void setRouteManager(RouteManager routeManager) {
        this.routeManager = routeManager;
    }

    private TopoManager topoManager;
    public TopoManager getTopoManager() {
        return this.topoManager;
    }
    public void setTopoManager(TopoManager topoManager) {
        this.topoManager = topoManager;
    }

    private SearchManager searchManager;
    public SearchManager getSearchManager() {
        return this.searchManager;
    }
    public void setSearchManager(SearchManager searchManager) {
        this.searchManager = searchManager;
    }
}
