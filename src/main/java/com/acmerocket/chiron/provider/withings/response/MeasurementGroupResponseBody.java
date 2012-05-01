package com.acmerocket.chiron.provider.withings.response;

import java.util.Date;
import java.util.List;

import com.acmerocket.chiron.provider.withings.model.MeasurementGroup;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MeasurementGroupResponseBody {
    private List<MeasurementGroup> groups;
    private Date updateTime;

    public List<MeasurementGroup> getGroups() {
        return groups;
    }

    @JsonProperty("measuregrps")
    public void setGroups(List<MeasurementGroup> groups) {
        this.groups = groups;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
    @JsonProperty("updatetime")
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}