package com.nicolasboueme.climbing.consumer.contract;

import com.nicolasboueme.climbing.consumer.contract.dao.*;

public interface DaoFactory {
    SpotDao getSpotDao();
    SectorDao getSectorDao();
    RouteDao getRouteDao();
    PublicationDao getPublicationDao();
    TopoDao getTopoDao();
}
