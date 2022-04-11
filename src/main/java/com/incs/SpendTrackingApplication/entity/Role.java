package com.incs.SpendTrackingApplication.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {
    @Id
    private String roleId;
    private String roleType;

    public Role(String roleId, String roleType) {
        this.roleId = roleId;
        this.roleType = roleType;
    }
    public Role(){}

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId='" + roleId + '\'' +
                ", roleType='" + roleType + '\'' +
                '}';
    }
}
