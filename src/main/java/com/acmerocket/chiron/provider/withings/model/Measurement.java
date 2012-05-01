package com.acmerocket.chiron.provider.withings.model;

import java.io.Serializable;


/**
 * A measure is an actual measurement of something. It has three parameters: A
 * type which can be one of the ones specified in the Measure types table. A
 * value which is the actual value of the measure (integer). A unit which
 * represents the power of 10 that has to be multipled by value to find the
 * actual data (integer).
 * 
 * @author jon
 */
public class Measurement implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static final float POUND = 0.453592f;
    private static final float FOOT = 0.0254f * 12;

    private MeasureType type;
    private float value;
    
    private int transportValue;
    private int unit;
    
    public Measurement() {};

    public Measurement(MeasureType type, int value, int unit) {
        this.transportValue = value;
        this.type = type;
        this.unit = unit;
    }
    
    public Measurement(int typeOrd, int value, int unit) {
        this.transportValue = value;
        this.type = MeasureType.valueOf(typeOrd);
        this.unit = unit;
    }

    public void setValue(int transportValue) {
        this.transportValue = transportValue;
        this.value = actualValue(transportValue, unit);
    }

    public float getValue() {
        return this.value;
    }
    
    public float getValue(MeasureSystem system) {
        switch (this.type) {
            case weight:
            case mass:
            case fatMass:
                if (MeasureSystem.IMPERIAL == system) {
                    return roundToHundred(this.value / POUND);
                }
                else {
                    return roundToHundred(this.value);
                }                
            case height:
                if (MeasureSystem.IMPERIAL == system) {
                    double feet = Math.round(this.value / FOOT);
                    return roundToHundred((float)feet);
                }
                else {
                    return roundToHundred(this.value);
                }
            default:
                return roundToHundred(this.value);
        }                
    }
    
    public MeasureType getType() {
        return this.type;
    }
    
    public void setType(MeasureType type) {
        this.type = type;
    }

    public void setUnit(int unit) {
        this.unit = unit;
        this.value = actualValue(this.transportValue, unit);
    }

    private static float actualValue(int transportValue, int unit) {
        return (float)(transportValue * Math.pow(10, unit));
    }

    /**
     * Returns a nicely formatted string.
     */
    public String getMeasurement() {
        return this.getMeasurement(MeasureSystem.IMPERIAL);
    }
    
    private void appendMeasure(StringBuilder result, MeasureType type, MeasureSystem ms) {
        switch (ms) {
            case IMPERIAL: 
                result.append(roundToHundred(this.getValue() / POUND)).append(" lb");
                break;
            case SI:
                result.append(roundToHundred(this.getValue())).append(" Kg");
                break;
        }
    }

    /**
     * Returns a nicely formatted string based on the requested System.
     */
    public String getMeasurement(MeasureSystem system) {
        if (this.type == null) {
            return "?type?";
        }
        
        StringBuilder result = new StringBuilder();
        
        result.append(this.type.getDescription()).append(' ');
        
        switch (this.type) {
            case weight:
            case mass:
            case fatMass:
                this.appendMeasure(result, getType(), system);
                break;
            case height:
                if (system == MeasureSystem.IMPERIAL) {
                    double inches = Math.round(this.getValue() / FOOT / 12);
                    int foot = Double.valueOf(Math.floor(inches / FOOT)).intValue();
                    int inch = Double.valueOf(inches - (FOOT * foot)).intValue();
                    result.append(foot);
                    result.append(" ft ");
                    result.append(inch);
                    result.append(" in");
                }
                else {
                    result.append(roundToHundred(this.getValue()));
                    result.append(" m");
                }
                break;
            case fatRatio:
                result.append(this.getValue());
                break;
            default:
                result.append("?");
        }

        return result.toString();
    }

    public enum MeasureSystem {
        IMPERIAL, SI;
    }

    /** */
    @Override
    public String toString() {
        //return "Measure [actualValue=" + this.getValue() + ", type=" + this.type + ", unit=" + this.unit
        //        + ", value=" + this.transportValue + ", imperial: " + this.getMeasure(MeasureSystem.IMPERIAL) + ", si: "
        //        + this.getMeasure(MeasureSystem.SI) + "]";
        
        return this.type + ": " + this.getValue(MeasureSystem.IMPERIAL);
    }

    public static float roundToHundred(float value) {        
        return Math.round(value * 100) / 100f;
    }
}
