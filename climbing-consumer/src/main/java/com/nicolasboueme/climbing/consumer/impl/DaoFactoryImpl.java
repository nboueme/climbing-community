package com.nicolasboueme.climbing.consumer.impl;

import com.nicolasboueme.climbing.consumer.contract.DaoFactory;
import com.nicolasboueme.climbing.consumer.contract.dao.*;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DaoFactoryImpl implements DaoFactory {

    @Inject
    private SpotDao spotDao;
    public SpotDao getSpotDao() {
        return spotDao;
    }

    @Inject
    private SectorDao sectorDao;
    public SectorDao getSectorDao() {
        return sectorDao;
    }

    @Inject
    private RouteDao routeDao;
    public RouteDao getRouteDao() {
        return routeDao;
    }

    @Inject
    private PublicationDao publicationDao;
    public PublicationDao getPublicationDao() {
        return publicationDao;
    }

    @Inject
    private TopoDao topoDao;
    public TopoDao getTopoDao() {
        return topoDao;
    }
}
