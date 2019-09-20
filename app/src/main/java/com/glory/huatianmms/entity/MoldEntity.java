package com.glory.huatianmms.entity;

/**
 * @author yi.zhang
 * @date 2019/9/19
 * @desc 治具实体类
 */
public class MoldEntity {

    private String MLOTID;

    private String MLOTTYPE;

    private String STATE;

    private String CLEANSTATE;

    private String LIMITLIFE;

    private String CURRENTCOUNT;

    private String GRADE1;

    private String GRADE2;

    private String MATERIALNAME;

    private String FLOORLIFEEXPIRE;

    public String getMLOTID() {
        return MLOTID == null ? "" : String.valueOf(MLOTID);
    }

    public void setMLOTID(String MLOTID) {
        this.MLOTID = MLOTID;
    }

    public String getMLOTTYPE() {
        return MLOTTYPE == null ? "" : String.valueOf(MLOTTYPE);
    }

    public void setMLOTTYPE(String MLOTTYPE) {
        this.MLOTTYPE = MLOTTYPE;
    }

    public String getSTATE() {
        return STATE == null ? "" : String.valueOf(STATE);
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getCLEANSTATE() {
        return CLEANSTATE == null ? "" : String.valueOf(CLEANSTATE);
    }

    public void setCLEANSTATE(String CLEANSTATE) {
        this.CLEANSTATE = CLEANSTATE;
    }

    public String getLIMITLIFE() {
        return LIMITLIFE == null ? "" : String.valueOf(LIMITLIFE);
    }

    public void setLIMITLIFE(String LIMITLIFE) {
        this.LIMITLIFE = LIMITLIFE;
    }

    public String getCURRENTCOUNT() {
        return CURRENTCOUNT == null ? "" : String.valueOf(CURRENTCOUNT);
    }

    public void setCURRENTCOUNT(String CURRENTCOUNT) {
        this.CURRENTCOUNT = CURRENTCOUNT;
    }

    public String getGRADE1() {
        return GRADE1 == null ? "" : String.valueOf(GRADE1);
    }

    public void setGRADE1(String GRADE1) {
        this.GRADE1 = GRADE1;
    }

    public String getGRADE2() {
        return GRADE2 == null ? "" : String.valueOf(GRADE2);
    }

    public void setGRADE2(String GRADE2) {
        this.GRADE2 = GRADE2;
    }

    public String getMATERIALNAME() {
        return MATERIALNAME == null ? "" : String.valueOf(MATERIALNAME);
    }

    public void setMATERIALNAME(String MATERIALNAME) {
        this.MATERIALNAME = MATERIALNAME;
    }

    public String getFLOORLIFEEXPIRE() {
        return FLOORLIFEEXPIRE == null ? "" : String.valueOf(FLOORLIFEEXPIRE);
    }

    public void setFLOORLIFEEXPIRE(String FLOORLIFEEXPIRE) {
        this.FLOORLIFEEXPIRE = FLOORLIFEEXPIRE;
    }
}
