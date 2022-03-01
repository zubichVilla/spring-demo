package com.zube.myfancypdfinvoices.service;

import com.zube.myfancypdfinvoices.model.User;

import java.util.UUID;

public class UserService {

    public User findById(String id){

        String randomName = UUID.randomUUID().toString();
        // dummy implementation - always returning an user
        return new User(id,randomName);
    }
}
