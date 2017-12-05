package com.nicolasboueme.climbing.consumer.contract.dao;

import com.nicolasboueme.climbing.model.entity.Topo;

import java.util.List;

public interface TopoDao {
    List<Topo> listTopo();
    void addTopo(Topo topo);
    Topo getTopo(Topo topo);
    void updateTopo(Topo topo);
    void deleteTopo(Topo topo);
}
