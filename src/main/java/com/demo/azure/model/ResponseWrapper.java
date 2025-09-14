package com.demo.azure.model;

import java.io.Serializable;

public class ResponseWrapper implements Serializable{
    
    private UserAction userAction ;
    private String reqReceivedTime ;
    private String resSendTime ;

    public void setUserAction(UserAction userAction) {
        this.userAction = userAction;
    }

    public void setReqReceivedTime(String reqReceivedTime) {
        this.reqReceivedTime = reqReceivedTime;
    }

    public void setResSendTime(String resSendTime) {
        this.resSendTime = resSendTime;
    }


}
