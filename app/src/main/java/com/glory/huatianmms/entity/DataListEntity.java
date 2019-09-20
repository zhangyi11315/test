package com.glory.huatianmms.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yi.zhang
 * @date 2019/9/19
 * @desc
 */
public class DataListEntity<T> {

    private List<T> DATALIST;

    public List<T> getDATALIST() {
        return DATALIST == null ? new ArrayList<>() :DATALIST;
    }

    public void setDATALIST(List<T> DATALIST) {
        this.DATALIST = DATALIST;
    }
}
