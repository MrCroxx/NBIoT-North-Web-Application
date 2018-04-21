package com.croxx.nbiot.jsonmsg;

public class DefaultMSG {
    private int statusCode;
    private Object content;

    public DefaultMSG(int statusCode,Object content){
        this.statusCode = statusCode;
        this.content = content;
    }

    /*    Getters & Setters     */

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
