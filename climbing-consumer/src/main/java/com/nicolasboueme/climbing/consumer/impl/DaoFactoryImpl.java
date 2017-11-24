package com.nicolasboueme.climbing.consumer.impl;

import com.nicolasboueme.climbing.consumer.contract.DaoFactory;
import com.nicolasboueme.climbing.consumer.contract.dao.*;

public class DaoFactoryImpl implements DaoFactory {

    private SpotDao spotDao;
    public SpotDao getSpotDao() {
        return spotDao;
    }
    public void setSpotDao(SpotDao spotDao) {
        this.spotDao = spotDao;
    }

    private SectorDao sectorDao;
    public SectorDao getSectorDao() {
        return sectorDao;
    }
    public void setSectorDao(SectorDao sectorDao) {
        this.sectorDao = sectorDao;
    }

    private RouteDao routeDao;
    public RouteDao getRouteDao() {
        return routeDao;
    }
    public void setRouteDao(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    private TopoDao topoDao;
    public TopoDao getTopoDao() {
        return topoDao;
    }
    public void setTopoDao(TopoDao topoDao) {
        this.topoDao = topoDao;
    }

    private PublicationDao publicationDao;
    public PublicationDao getPublicationDao() {
        return publicationDao;
    }
    public void setPublicationDao(PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
    }
}
