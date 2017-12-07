package com.nicolasboueme.climbing.consumer.contract.dao;

import com.nicolasboueme.climbing.model.entity.Sector;

import java.util.List;

public interface SectorDao {
    List<Sector> listSectorsFromParent(Sector sector);

    void addSector(Sector sector);

    void updateSector(Sector sector);

    void deleteSector(Sector sector);
}
