package com.alexander.scratchpad.jwt;

public class ExpiringToken {

    private String sub;
    private Long exp;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }
}
