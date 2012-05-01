package com.acmerocket.chiron.provider.withings.response;

import java.util.List;

import com.acmerocket.chiron.provider.withings.model.User;

public class GetUsersBody {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
