package com.video.platform.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: yangl
 * Date: 13-7-28 下午10:34
 */
@Entity
@Table(name = "ss_bill")
public class Bill extends IdEntity {
    private int cpId;
    private int parentId;
    private String cpName;
    private int users;
    private int cpUsers;
    private String Date;

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

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public int getCpUsers() {
        return cpUsers;
    }

    public void setCpUsers(int cpUsers) {
        this.cpUsers = cpUsers;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
