package com.nicolasboueme.climbing.consumer.contract.dao;

import com.nicolasboueme.climbing.model.entity.Spot;

import java.util.List;

public interface SpotDao {
    List<Spot> listSpots();
}
