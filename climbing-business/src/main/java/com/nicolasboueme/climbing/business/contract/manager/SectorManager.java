package com.nicolasboueme.climbing.business.contract.manager;

import com.nicolasboueme.climbing.model.entity.Sector;

import java.util.List;

public interface SectorManager {

    List<Sector> listSectorsFromParent(int spotId);
}
