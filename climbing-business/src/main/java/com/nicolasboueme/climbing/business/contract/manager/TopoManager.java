package com.nicolasboueme.climbing.business.contract.manager;

import com.nicolasboueme.climbing.model.entity.Topo;

import java.util.List;

public interface TopoManager {
    List<Topo> listTopo();

    void addTopo(Topo topo);

    Topo getTopo(Topo topo);

    void updateTopo(Topo topo);

    void deleteTopo(Topo topo);
}
