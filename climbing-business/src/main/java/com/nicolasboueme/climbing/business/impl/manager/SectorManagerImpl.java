package com.nicolasboueme.climbing.business.impl.manager;

import com.nicolasboueme.climbing.business.contract.manager.SectorManager;
import com.nicolasboueme.climbing.business.impl.AbstractManager;
import com.nicolasboueme.climbing.model.entity.Sector;

import java.util.List;

public class SectorManagerImpl extends AbstractManager implements SectorManager {

    public List<Sector> listSectorsFromParent(Sector sector) {
        return getDaoFactory().getSectorDao().listSectorsFromParent(sector);
    }

    public void addSector(Sector sector) {
        getDaoFactory().getSectorDao().addSector(sector);
    }

    public void updateSector(Sector sector) {
        getDaoFactory().getSectorDao().updateSector(sector);
    }

    public void deleteSector(Sector sector) {
        getDaoFactory().getSectorDao().deleteSector(sector);
    }
}
