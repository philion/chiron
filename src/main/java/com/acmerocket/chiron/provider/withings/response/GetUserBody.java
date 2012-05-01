package com.acmerocket.chiron.provider.withings.response;

import com.acmerocket.chiron.provider.withings.model.User;


public class GetUserBody {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
