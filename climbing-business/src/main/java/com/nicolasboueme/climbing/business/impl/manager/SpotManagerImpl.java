package com.nicolasboueme.climbing.business.impl.manager;

import com.nicolasboueme.climbing.business.contract.manager.SpotManager;
import com.nicolasboueme.climbing.business.impl.AbstractManager;
import com.nicolasboueme.climbing.model.entity.Spot;

import java.util.List;

public class SpotManagerImpl extends AbstractManager implements SpotManager {

    public List<Spot> listSpots() {
        return getDaoFactory().getSpotDao().listSpots();
    }
}
