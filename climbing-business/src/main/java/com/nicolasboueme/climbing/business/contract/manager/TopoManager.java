package com.nicolasboueme.climbing.business.contract.manager;

import com.nicolasboueme.climbing.model.entity.Spot;
import com.nicolasboueme.climbing.model.entity.Topo;
import com.nicolasboueme.climbing.model.entity.UserAccount;

import java.util.List;

public interface TopoManager {
    List<Topo> listTopo();

    void addTopo(Topo topo);

    Topo getTopo(Topo topo);

    void deleteTopoPicture(Topo topo);

    void updateTopo(Topo topo);

    void deleteTopo(Topo topo);

    void addTopoHasSpot(Topo topo, Spot spot);

    List<Spot> getNotRelatedSpots(Topo topo);

    List<Topo> getTopoHasSpot(Topo topo);

    void deleteTopoHastSpot(Topo topo, Spot spot);

    void addUserHasTopo(UserAccount user);

    boolean getNotRelatedUser(Topo topo);

    List<UserAccount> getUserHasTopo(Topo topo);

    void updateUserHasTopo(UserAccount user);

    void deleteUserHasTopo(UserAccount user);
}
