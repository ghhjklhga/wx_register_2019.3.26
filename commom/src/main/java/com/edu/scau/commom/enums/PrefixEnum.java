package com.edu.scau.commom.enums;


public enum PrefixEnum {

    REDIS_PREFIX("user_redis_session_");
    ;
    private String context;

    PrefixEnum(String context) {
        this.context = context;
    }

    public String getContext() {
        return context;
    }
}
