package com.alexander.scratchpad.jwt;

public class ServiceToken {
    String service;
    public ServiceToken(String service){
        this.service = service;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
