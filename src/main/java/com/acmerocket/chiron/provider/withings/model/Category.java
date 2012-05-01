package com.acmerocket.chiron.provider.withings.model;

import java.util.HashMap;
import java.util.Map;

public enum Category {
    unknown(0, "?"),  // FIXME REMOVE
    measure(1, "Measure"), 
    target(2, "Target");

    private int ordinal;
    private String description;

    private Category(int ordinal, String description) {
        this.ordinal = ordinal;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public int getOrdinal() {
        return this.ordinal;
    }

    public static Category valueOf(int type) {
        return ordMap.get(type);
    }

    private static final Map<Integer, Category> ordMap = new HashMap<Integer, Category>();
    static {
        for (Category type : Category.values()) {
            ordMap.put(type.getOrdinal(), type);
        }
    }
}
