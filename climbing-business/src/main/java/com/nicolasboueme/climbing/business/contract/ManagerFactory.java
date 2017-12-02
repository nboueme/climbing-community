package com.nicolasboueme.climbing.business.contract;

import com.nicolasboueme.climbing.business.contract.manager.SpotManager;
import com.nicolasboueme.climbing.business.contract.manager.RouteManager;
import com.nicolasboueme.climbing.business.contract.manager.PublicationManager;
import com.nicolasboueme.climbing.business.contract.manager.SectorManager;
import com.nicolasboueme.climbing.business.contract.manager.TopoManager;

public interface ManagerFactory {
    SpotManager getSpotManager();
    void setSpotManager(SpotManager spotManager);

    SectorManager getSectorManager();
    void setSectorManager(SectorManager sectorManager);

    RouteManager getRouteManager();
    void setRouteManager(RouteManager routeManager);

    TopoManager getTopoManager();
    void setTopoManager(TopoManager topoManager);

    PublicationManager getPublicationManager();
    void setPublicationManager(PublicationManager searchManager);
}
