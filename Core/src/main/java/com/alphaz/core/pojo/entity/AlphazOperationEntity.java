package com.alphaz.core.pojo.entity;

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
@Table(name = "alphaz_operation", schema = "alphaz", catalog = "")
public class AlphazOperationEntity extends BaseDO {
    private String operationname;
    private String label;
    private Integer sort;
    private String icon;



    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
    @Column(name = "operationname")
    public String getOperationname() {
        return operationname;
    }

    public void setOperationname(String operationname) {
        this.operationname = operationname;
    }

    @Basic
    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }



}
