package com.nicolasboueme.climbing.consumer.dao;

import com.nicolasboueme.climbing.model.entity.Topo;

import java.util.List;

public interface TopoDao {
    List<Topo> listTopo();
    Topo getTopo(int topoId);
}
