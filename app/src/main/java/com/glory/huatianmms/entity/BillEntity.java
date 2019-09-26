package com.glory.huatianmms.entity;

import java.io.Serializable;

/**
 * @author hx
 * @date 2019/9/26
 * @desc
 */
public class BillEntity implements Serializable {

    /**
     * DOCID : aaa
     * OWNER : AAA
     * COMMENTS : AAA
     */

    private String DOCID;
    private String OWNER;
    private String COMMENTS;

    public String getDOCID() {
        return DOCID == null ? "" : DOCID;
    }

    public void setDOCID(String DOCID) {
        this.DOCID = DOCID;
    }

    public String getOWNER() {
        return OWNER == null ? "" : OWNER;
    }

    public void setOWNER(String OWNER) {
        this.OWNER = OWNER;
    }

    public String getCOMMENTS() {
        return COMMENTS == null ? "" : COMMENTS;
    }

    public void setCOMMENTS(String COMMENTS) {
        this.COMMENTS = COMMENTS;
    }


}
