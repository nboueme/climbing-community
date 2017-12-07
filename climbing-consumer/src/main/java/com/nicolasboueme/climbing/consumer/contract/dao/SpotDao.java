package com.nicolasboueme.climbing.consumer.contract.dao;

import com.nicolasboueme.climbing.model.entity.Spot;

import java.util.List;

public interface SpotDao {
    List<Spot> listSpots();

    void addSpot(Spot spot);

    void updateSpot(Spot spot);

    void deleteSpot(Spot spot);
}
