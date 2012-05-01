package com.acmerocket.chiron.provider.withings.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author philion
 *
 */
public enum StatusCode {
    
    success(0, "Operation was successfull"),
    
    invalidHash(100, "The hash is missing, invalid, or does not match the provided email"),
    invalidUserId(247, "The userid provided is absent, or incorrect"),
    privateUser(250, "The userid and publickey provided do not match, or the user does not share its data."),
    
    invalidEmail(264, "The email address provided is either unknown or invalid"),
    invalidCallback(293, "The callback URL is either absent or incorrect"),
    couldNotDelete(294, "No such subscription could be deleted"),
    subscriptionNotFound(286, "No such subscription was found"),
    tempFailure(284, "Temporary failure"),
    
    unknownError(2555, "An unknown error occured");

    private int ordinal;
    private String description;

    private StatusCode(int ordinal, String description) {
        this.ordinal = ordinal;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public int getOrdinal() {
        return this.ordinal;
    }

    private static final Map<Integer, StatusCode> ordMap = new HashMap<Integer, StatusCode>();
    
    static {
        for (StatusCode type : StatusCode.values()) {
            ordMap.put(type.getOrdinal(), type);
        }
    }
    
    public static StatusCode valueOf(int ordinal) {
        return ordMap.get(ordinal);
    }
}
