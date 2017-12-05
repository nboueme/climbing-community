package com.nicolasboueme.climbing.consumer.contract.dao;

import com.nicolasboueme.climbing.model.entity.UserAccount;

public interface UserDao {
    void addUser(UserAccount user);

    UserAccount getUser(UserAccount user);

    void updateUser(UserAccount user);

    void deleteUser(UserAccount user);
}
