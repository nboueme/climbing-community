package com.nicolasboueme.climbing.business.impl.manager;

import com.nicolasboueme.climbing.business.contract.manager.TopoManager;
import com.nicolasboueme.climbing.business.impl.AbstractManager;
import com.nicolasboueme.climbing.consumer.impl.DaoFactoryImpl;
import com.nicolasboueme.climbing.consumer.contract.dao.TopoDao;
import com.nicolasboueme.climbing.model.entity.Topo;

import java.util.List;

public class TopoManagerImpl extends AbstractManager implements TopoManager {

    public List<Topo> listTopo() {
        return getDaoFactory().getTopoDao().listTopo();
    }

    public Topo getTopo(int topoId) {
        return getDaoFactory().getTopoDao().getTopo(topoId);
    }
}
