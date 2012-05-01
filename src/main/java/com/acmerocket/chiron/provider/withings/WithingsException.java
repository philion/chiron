package com.acmerocket.chiron.provider.withings;

import com.acmerocket.chiron.provider.withings.model.StatusCode;


public class WithingsException extends Exception {
    private static final long serialVersionUID = 1L;
    
    private final StatusCode code;
    
    public WithingsException(Throwable ex) {
        super(ex);
        this.code = StatusCode.unknownError;
    }
    
    public WithingsException(Throwable ex, int code) {
        super(ex);
        this.code = StatusCode.unknownError;
    }

    public WithingsException(String message, int code) {
        super(message);
        this.code = StatusCode.valueOf(code);
    }

    public WithingsException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = StatusCode.valueOf(code);
    }

    public WithingsException(StatusCode status) {
        super(status.getDescription());
        this.code = status;
    }

    public StatusCode getCode() {
        return this.code;
    }
}
