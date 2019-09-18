package com.glory.huatianmms.entity.response;

/**
 * @author yi.zhang
 * @date 2019/9/2
 * @desc
 */
public class ResponseHeader {

    public String getTRANSACTIONID() {
        return TRANSACTIONID == null ? "" : String.valueOf(TRANSACTIONID);
    }

    public void setTRANSACTIONID(String TRANSACTIONID) {
        this.TRANSACTIONID = TRANSACTIONID;
    }

    public String getMESSAGERRN() {
        return MESSAGERRN == null ? "" : String.valueOf(MESSAGERRN);
    }

    public void setMESSAGERRN(String MESSAGERRN) {
        this.MESSAGERRN = MESSAGERRN;
    }

    public String getRESULT() {
        return RESULT == null ? "" : String.valueOf(RESULT);
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public String getRESULTCODE() {
        return RESULTCODE == null ? "" : String.valueOf(RESULTCODE);
    }

    public void setRESULTCODE(String RESULTCODE) {
        this.RESULTCODE = RESULTCODE;
    }

    public String getRESULTMESSAGE() {
        return RESULTMESSAGE == null ? "" : String.valueOf(RESULTMESSAGE);
    }

    public void setRESULTMESSAGE(String RESULTMESSAGE) {
        this.RESULTMESSAGE = RESULTMESSAGE;
    }

    private String TRANSACTIONID;

    private String MESSAGERRN;

    private String RESULT;

    private String RESULTCODE;

    private String RESULTMESSAGE;
}
