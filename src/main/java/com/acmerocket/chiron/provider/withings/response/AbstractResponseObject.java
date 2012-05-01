package com.acmerocket.chiron.provider.withings.response;

import com.acmerocket.chiron.provider.withings.model.StatusCode;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractResponseObject<T> implements ResponseObject<T> {
    private StatusCode status;
    private T body;
    
    public StatusCode getStatus() {
        return status;
    }
    
    @JsonProperty("status")
    public void setStatus(StatusCode code) {
        this.status = code;
    }
    
    public T getBody() {
        return this.body;
    }
    
    @JsonProperty("body")
    public void setBody(T body) {
        this.body = body;
    }
}
