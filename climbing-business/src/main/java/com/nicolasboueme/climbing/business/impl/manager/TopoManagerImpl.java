package com.nicolasboueme.climbing.business.impl.manager;

import com.nicolasboueme.climbing.business.contract.manager.TopoManager;
import com.nicolasboueme.climbing.business.impl.AbstractManager;
import com.nicolasboueme.climbing.model.entity.Spot;
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

    public void addTopoHasSpot(Topo topo, Spot spot) {
        getDaoFactory().getTopoDao().addTopoHasSpot(topo, spot);
    }

    public List<Spot> getNotRelatedSpots(Topo topo) {
        return getDaoFactory().getTopoDao().getNotRelatedSpots(topo);
    }

    public List<Topo> getTopoHasSpot(Topo topo) {
        return getDaoFactory().getTopoDao().getTopoHasSpot(topo);
    }

    public void deleteTopoHastSpot(Topo topo, Spot spot) {
        getDaoFactory().getTopoDao().deleteTopoHastSpot(topo, spot);
    }

    public void addUserHasTopo(UserAccount user) {
        getDaoFactory().getTopoDao().addUserHasTopo(user);
    }

    public boolean getNotRelatedUser(Topo topo) {
        return getDaoFactory().getTopoDao().getNotRelatedUser(topo);
    }

    public List<UserAccount> getUserHasTopo(Topo topo) {
        return getDaoFactory().getTopoDao().getUserHasTopo(topo);
    }

    public void updateUserHasTopo(UserAccount user) {
        getDaoFactory().getTopoDao().updateUserHasTopo(user);
    }

    public void deleteUserHasTopo(UserAccount user) {
        getDaoFactory().getTopoDao().deleteUserHasTopo(user);
    }
}
