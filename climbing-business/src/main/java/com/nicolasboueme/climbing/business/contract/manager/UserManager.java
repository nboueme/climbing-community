package com.nicolasboueme.climbing.business.contract.manager;

import com.nicolasboueme.climbing.model.entity.UserAccount;

public interface UserManager {
    UserAccount userLogin(String login, String password);

    void registerNewUserAccount(UserAccount user);
}
