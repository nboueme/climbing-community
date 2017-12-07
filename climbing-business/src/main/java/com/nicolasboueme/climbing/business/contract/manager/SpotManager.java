package com.nicolasboueme.climbing.business.contract.manager;

import com.nicolasboueme.climbing.model.entity.Spot;

import java.util.List;

public interface SpotManager {
    List<Spot> listSpots();

    void addSpot(Spot spot);

    void updateSpot(Spot spot);

    void deleteSpot(Spot spot);
}
