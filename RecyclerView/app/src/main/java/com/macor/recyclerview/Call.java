package com.macor.recyclerview;

public class Call {

    private String callerName;
    private String callTime;

    public Call(String callerNameR, String callTime) {
        this.callerName = callerNameR;
        this.callTime = callTime;
    }

    public String getCallerName() {
        return callerName;
    }

    public String getCallTime() {
        return callTime;
    }
}
