package com.acmerocket.chiron.provider.withings.model;

import java.util.HashMap;
import java.util.Map;

/**
 * From http://www.withings.com/fr/api/balance#getmeas
 * 
 * @author philion
 */
public enum MeasureType {
    
    // FIXME: Need a custom mapper, but I can't figure out how they work. Can't even find example code.
    
    unknown(0, "?"),
    weight(1, "Weight (kg)"),
    unknown2(2, "?"),
    unknown3(3, "?"),
    height(4, "Height (meter)"),
    mass(5, "Fat Free Mass (kg)"),
    fatRatio(6, "Fat Ratio (%)"),
    unknown7(7, "?"),
    fatMass(8, "Fat Mass Weight"),
    bpDiastolic(9, "Diastolic Blood Pressure (mmHg)"),
    bpSystolic(10, "Systolic Blood Pressure (mmHg)"),
    pulse(11, "Heart Pulse (bpm)");

    private int ordinal;
	private String description;

	private MeasureType(int type, String description) {
	    this.ordinal = type;
		this.description = description;
	}
	
	public int getOrdinal() {
	    return this.ordinal;
	}

	public String getDescription() {
		return this.description;
	}
	
    private static final Map<Integer, MeasureType> typeMap = new HashMap<Integer, MeasureType>();
    
    static {
        for (MeasureType type : MeasureType.values()) {
            typeMap.put(type.getOrdinal(), type);
        }
    }
	
	public static MeasureType valueOf(int type) {
	    return typeMap.get(type);
	}
}
