package com.acmerocket.chiron.provider.withings;

import com.acmerocket.chiron.provider.withings.model.User;
import com.acmerocket.chiron.spi.Credentials;

public class WithingsCredentials implements Credentials {
    private static final long serialVersionUID = 402155052095261943L;
    
    private long id;
    private String key;

    public WithingsCredentials(long id, String key) {
        this.id = id;
        this.key = key;
    }
    
    public WithingsCredentials(User user) {
        this(user.getId(), user.getPublicKey());
    }

    public long getId() {
        return id;
    }
    
    public String getKey() {
        return key;
    }    
}
