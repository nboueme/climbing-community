package com.nicolasboueme.climbing.business.impl.manager;

import com.nicolasboueme.climbing.business.contract.manager.UserManager;
import com.nicolasboueme.climbing.business.impl.AbstractManager;
import com.nicolasboueme.climbing.model.entity.UserAccount;

public class UserManagerImpl extends AbstractManager implements UserManager {

    public UserAccount userLogin(String login, String password) {
        return getDaoFactory().getUserDao().userLogin(login, password);
    }

    public void registerNewUserAccount(UserAccount user) {
        getDaoFactory().getUserDao().addUser(user);
    }
}
