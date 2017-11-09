package com.nicolasboueme.climbing.consumer.dao;

import com.nicolasboueme.climbing.model.beans.Topo;

import java.util.List;

public interface TopoDao {
    List<Topo> listTopo();
    Topo getTopo(int publicationId);
}
