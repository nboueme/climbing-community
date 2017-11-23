package com.nicolasboueme.climbing.business.impl.manager;

import com.nicolasboueme.climbing.business.contract.manager.SectorManager;
import com.nicolasboueme.climbing.business.impl.AbstractManager;
import com.nicolasboueme.climbing.model.entity.Sector;

import java.util.List;

public class SectorManagerImpl extends AbstractManager implements SectorManager {

    public List<Sector> listSectorsFromParent(int spotId) {
        return getDaoFactory().getSectorDao().listSectorsFromParent(spotId);
    }
}
