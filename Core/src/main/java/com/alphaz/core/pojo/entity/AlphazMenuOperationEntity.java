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
@Table(name = "alphaz_menu_operation", schema = "alphaz", catalog = "")
public class AlphazMenuOperationEntity extends BaseDO {
    private Long menuid;
    private Long operationid;


    @Basic
    @Column(name = "menuid")
    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    @Basic
    @Column(name = "operationid")
    public Long getOperationid() {
        return operationid;
    }

    public void setOperationid(Long operationid) {
        this.operationid = operationid;
    }



}
