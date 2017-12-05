package com.nicolasboueme.climbing.business.impl.manager;

import com.nicolasboueme.climbing.business.contract.manager.UserManager;
import com.nicolasboueme.climbing.business.impl.AbstractManager;
import com.nicolasboueme.climbing.model.entity.UserAccount;

public class UserManagerImpl extends AbstractManager implements UserManager {

    public void registerNewUserAccount(UserAccount user) {
        getDaoFactory().getUserDao().addUser(user);
    }

    public UserAccount userLogin(UserAccount user) {
        return getDaoFactory().getUserDao().getUser(user);
    }

    public void updateUser(UserAccount user) {
        getDaoFactory().getUserDao().updateUser(user);
    }

    public void deleteUser(UserAccount user) {
        getDaoFactory().getUserDao().deleteUser(user);
    }
}
