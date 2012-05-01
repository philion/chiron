package com.acmerocket.chiron.provider.withings.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Measure group content fields Name Description grpid A unique grpid (Group
 * Id), useful for performing synchronization tasks. attrib An attrib
 * (Attribute), defining the way the measure was attributed to the user. It may
 * take the values shown in the Attribution status table below. date The date
 * (EPOCH format) at which the measure was taken or entered. category The
 * category of the group. A measure group can represent either measures captured
 * by the BodyScale or objectives set by the user. The category field indicates
 * for each measure group whether the measures in the group are measurements or
 * targets. Refer to the Category values table below. measures An array of
 * measures that belong to this particular measure group.
 * 
 * @author jon
 */
public class MeasurementGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int groupId;
    private AttributionStatus attributionStatus;
    private Date date;
    private Category category;
    private List<Measurement> measures;

    /** */
    public MeasurementGroup() {}

    /** */
    public MeasurementGroup(int groupId, AttributionStatus attributionStatus, Date date, Category category,
            List<Measurement> measures) {
        this.groupId = groupId;
        this.attributionStatus = attributionStatus;
        this.date = date;
        this.category = category;
        this.measures = measures;
    }

    /** */
    public int getGroupId() {
        return this.groupId;
    }

    @JsonProperty("grpid")
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public AttributionStatus getAttributionStatus() {
        return this.attributionStatus;
    }

    @JsonProperty("attrib")
    public void setAttributionStatus(AttributionStatus attributionStatus) {
        this.attributionStatus = attributionStatus;
    }

    public Date getDate() {
        return this.date;
    }

    @JsonProperty("date")
    public void setDate(Date creationDate) {
        this.date = creationDate;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Measurement> getMeasures() {
        return this.measures;
    }

    public void setMeasures(List<Measurement> measures) {
        this.measures = measures;
    }

    @Override
    public String toString() {
        return "MeasureGroup [attributionStatus=" + this.attributionStatus + ", category=" + this.category
                + ", date=" + this.date + ", groupId=" + this.groupId + ", measures=" + this.measures
                + "]";
    }
}
