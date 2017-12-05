package com.nicolasboueme.climbing.business.contract.manager;

import com.nicolasboueme.climbing.model.entity.UserAccount;

public interface UserManager {
    void registerNewUserAccount(UserAccount user);

    UserAccount userLogin(UserAccount user);

    void updateUser(UserAccount user);

    void deleteUser(UserAccount user);
}
