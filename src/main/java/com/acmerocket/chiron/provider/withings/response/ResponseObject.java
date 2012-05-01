package com.acmerocket.chiron.provider.withings.response;

import com.acmerocket.chiron.provider.withings.model.StatusCode;

public interface ResponseObject<T> {
    
    public StatusCode getStatus();
    public T getBody();
    
}
