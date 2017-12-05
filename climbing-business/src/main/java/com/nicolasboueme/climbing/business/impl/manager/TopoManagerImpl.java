package com.nicolasboueme.climbing.business.impl.manager;

import com.nicolasboueme.climbing.business.contract.manager.TopoManager;
import com.nicolasboueme.climbing.business.impl.AbstractManager;
import com.nicolasboueme.climbing.consumer.impl.DaoFactoryImpl;
import com.nicolasboueme.climbing.consumer.contract.dao.TopoDao;
import com.nicolasboueme.climbing.model.entity.Topo;
import com.nicolasboueme.climbing.model.entity.UserAccount;

import java.util.List;

public class TopoManagerImpl extends AbstractManager implements TopoManager {

    public List<Topo> listTopo() {
        return getDaoFactory().getTopoDao().listTopo();
    }

    public void addTopo(Topo topo) {
        getDaoFactory().getTopoDao().addTopo(topo);
    }

    public Topo getTopo(Topo topo) {
        return getDaoFactory().getTopoDao().getTopo(topo);
    }

    public void updateTopo(Topo topo) {
        getDaoFactory().getTopoDao().updateTopo(topo);
    }

    public void deleteTopo(Topo topo) {
        getDaoFactory().getTopoDao().deleteTopo(topo);
    }
}
