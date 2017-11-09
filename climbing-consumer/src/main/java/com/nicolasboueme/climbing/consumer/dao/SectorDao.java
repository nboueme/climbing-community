package com.nicolasboueme.climbing.consumer.dao;

import com.nicolasboueme.climbing.model.beans.Sector;

import java.util.List;

public interface SectorDao {
    List<Sector> listSectorsFromParent(int spotId);
}
