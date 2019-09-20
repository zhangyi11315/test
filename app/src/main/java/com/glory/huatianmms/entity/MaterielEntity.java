package com.glory.huatianmms.entity;

/**
 * @author yi.zhang
 * @date 2019/9/19
 * @desc 物料实体类
 */
public class MaterielEntity {

    private String MLOTID;

    private String MAINQTY;

    private String STATE;

    private String MATERIALDESC;

    private String MATERIALNAME;

    private String GRADE1;

    private String GRADE2;

    private String PARTNERCODE;

    private String FLOORLIFEEXPIRE;

    public String getMLOTID() {
        return MLOTID == null ? "" : String.valueOf(MLOTID);
    }

    public void setMLOTID(String MLOTID) {
        this.MLOTID = MLOTID;
    }

    public String getMAINQTY() {
        return MAINQTY == null ? "" : String.valueOf(MAINQTY);
    }

    public void setMAINQTY(String MAINQTY) {
        this.MAINQTY = MAINQTY;
    }

    public String getSTATE() {
        return STATE == null ? "" : String.valueOf(STATE);
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getMATERIALDESC() {
        return MATERIALDESC == null ? "" : String.valueOf(MATERIALDESC);
    }

    public void setMATERIALDESC(String MATERIALDESC) {
        this.MATERIALDESC = MATERIALDESC;
    }

    public String getMATERIALNAME() {
        return MATERIALNAME == null ? "" : String.valueOf(MATERIALNAME);
    }

    public void setMATERIALNAME(String MATERIALNAME) {
        this.MATERIALNAME = MATERIALNAME;
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

    public String getPARTNERCODE() {
        return PARTNERCODE == null ? "" : String.valueOf(PARTNERCODE);
    }

    public void setPARTNERCODE(String PARTNERCODE) {
        this.PARTNERCODE = PARTNERCODE;
    }

    public String getFLOORLIFEEXPIRE() {
        return FLOORLIFEEXPIRE == null ? "" : String.valueOf(FLOORLIFEEXPIRE);
    }

    public void setFLOORLIFEEXPIRE(String FLOORLIFEEXPIRE) {
        this.FLOORLIFEEXPIRE = FLOORLIFEEXPIRE;
    }
}
