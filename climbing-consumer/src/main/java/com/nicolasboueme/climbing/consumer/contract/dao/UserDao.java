package com.nicolasboueme.climbing.consumer.contract.dao;

import com.nicolasboueme.climbing.model.entity.UserAccount;

public interface UserDao {
    UserAccount userLogin(String login, String password);

    void addUser(UserAccount user);
}
