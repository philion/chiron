package com.acmerocket.chiron.provider.withings.model;

import java.util.HashMap;
import java.util.Map;

/**
 * From: http://www.withings.com/fr/api/balance#getmeas
 * 
 * @author philion
 */
public enum AttributionStatus {
    measured(0, "The data has been captured by a device and is known to belong to this user (and is not ambiguous)"), 
    ambiguous(1, "The data has been captured by a device but may belong to other users as well as this one (it is ambiguous)"), 
    manual(2, "The data has been entered manually for this particular use"), 
    unknown(3, "?"), // FIXME REMOVE
    creation(4, "The data has been entered manually during user creation (and may not be accurate)");

    private int ordinal;
    private String description;

    private AttributionStatus(int ordinal, String description) {
        this.ordinal = ordinal;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public int getOrdinal() {
        return this.ordinal;
    }

    private static final Map<Integer, AttributionStatus> typeMap = new HashMap<Integer, AttributionStatus>();
    
    static {
        for (AttributionStatus type : AttributionStatus.values()) {
            typeMap.put(type.getOrdinal(), type);
        }
    }
    
    public static AttributionStatus valueOf(int type) {
        return typeMap.get(type);
    }
}
