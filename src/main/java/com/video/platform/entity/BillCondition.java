package com.video.platform.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: yangl
 * Date: 13-7-30 下午9:26
 */
public class BillCondition {
    private int cpId;
    private int parentId;
    private String btime;
    private String etime;

    public BillCondition() {
    }

    public BillCondition(int cpId,int parentId, String btime, String etime) {
        this.cpId = cpId;
        this.parentId = parentId;
        this.btime = btime;
        this.etime = etime;
    }

    public int getCpId() {
        return cpId;
    }

    public void setCpId(int cpId) {
        this.cpId = cpId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getBtime() {
        return btime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
