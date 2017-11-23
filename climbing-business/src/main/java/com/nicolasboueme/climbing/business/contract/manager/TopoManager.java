package com.nicolasboueme.climbing.business.contract.manager;

import com.nicolasboueme.climbing.model.entity.Topo;

import java.util.List;

public interface TopoManager {
    List<Topo> listTopo();

    Topo getTopo(int topoId);
}
