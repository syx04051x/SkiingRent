package com.alphaz.core.pojo.entity;

import com.alphaz.core.constant.DataState;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.entity
 * User: C0dEr
 * Date: 2017/6/13
 * Time: 下午2:40
 * Description:This is a class of com.alphaz.core.pojo.entity
 */
@Entity
@Table(name = "alphaz_role", schema = "alphaz", catalog = "")
public class AlphazRoleEntity extends BaseDO {
    private String rolename;
    private String label;
    private String privilgelevel;
    private String description;
    private Integer sort;
    private boolean iseditable;

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "iseditable")
    public boolean getIseditable() {
        return iseditable;
    }

    public void setIseditable(boolean iseditable) {
        this.iseditable = iseditable;
    }


    @Basic
    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Basic
    @Column(name = "rolename")
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Basic
    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Basic
    @Column(name = "privilgelevel")
    public String getPrivilgelevel() {
        return privilgelevel;
    }

    public void setPrivilgelevel(String privilgelevel) {
        this.privilgelevel = privilgelevel;
    }


}
