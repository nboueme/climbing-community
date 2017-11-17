package com.nicolasboueme.climbing.consumer.dao;

import com.nicolasboueme.climbing.model.entity.Sector;

import java.util.List;

public interface SectorDao {
    List<Sector> listSectorsFromParent(int spotId);
}
