package com.glory.huatianmms.entity;

/**
 * @author yi.zhang
 * @date 2019/9/2
 * @desc 用户实体类
 */
public class UserEntity {

    public String getUSERNAME() {
        return USERNAME == null ? "" : String.valueOf(USERNAME);
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION == null ? "" : String.valueOf(DESCRIPTION);
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getDEFAULTLANGUAGE() {
        return DEFAULTLANGUAGE == null ? "" : String.valueOf(DEFAULTLANGUAGE);
    }

    public void setDEFAULTLANGUAGE(String DEFAULTLANGUAGE) {
        this.DEFAULTLANGUAGE = DEFAULTLANGUAGE;
    }

    public String getDEFAULTORGRRN() {
        return DEFAULTORGRRN == null ? "" : String.valueOf(DEFAULTORGRRN);
    }

    public void setDEFAULTORGRRN(String DEFAULTORGRRN) {
        this.DEFAULTORGRRN = DEFAULTORGRRN;
    }

    private String USERNAME;

    private String DESCRIPTION;

    private String DEFAULTLANGUAGE;

    private String DEFAULTORGRRN;
}
