package com.glory.huatianmms.entity.request;

/**
 * @author yi.zhang
 * @date 2019/6/10
 * @desc 请求消息头部
 */
public class MessageHeader {

    public String getMESSAGENAME() {
        return MESSAGENAME == null ? "" : String.valueOf(MESSAGENAME);
    }

    public void setMESSAGENAME(String MESSAGENAME) {
        this.MESSAGENAME = MESSAGENAME;
    }

    public String getTRANSACTIONID() {
        return TRANSACTIONID == null ? "" : String.valueOf(TRANSACTIONID);
    }

    public void setTRANSACTIONID(String TRANSACTIONID) {
        this.TRANSACTIONID = TRANSACTIONID;
    }

    public String getORGRRN() {
        return ORGRRN == null ? "" : String.valueOf(ORGRRN);
    }

    public void setORGRRN(String ORGRRN) {
        this.ORGRRN = ORGRRN;
    }

    public String getUSERNAME() {
        return USERNAME == null ? "" : String.valueOf(USERNAME);
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    private String MESSAGENAME;

    private String TRANSACTIONID;

    private String ORGRRN;

    private String USERNAME;


}
