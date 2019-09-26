package com.glory.huatianmms.entity;

import java.io.Serializable;

/**
 * @author hx
 * @date 2019/9/26
 * @desc
 */
public class LotEntity implements Serializable {
    private String LOTID;
    private String MLOTID;
    private double MAINQTY;
    private double TRANSMAINQTY;

    public String getMLOTID() {
        return MLOTID == null ? "" : MLOTID;
    }

    public void setMLOTID(String MLOTID) {
        this.MLOTID = MLOTID;
    }

    public String getLOTID() {
        return LOTID == null ? "" : LOTID;
    }

    public void setLOTID(String LOTID) {
        this.LOTID = LOTID;
    }

    public double getMAINQTY() {
        return MAINQTY;
    }

    public void setMAINQTY(double MAINQTY) {
        this.MAINQTY = MAINQTY;
    }

    public double getTRANSMAINQTY() {
        return TRANSMAINQTY;
    }

    public void setTRANSMAINQTY(double TRANSMAINQTY) {
        this.TRANSMAINQTY = TRANSMAINQTY;
    }
}
