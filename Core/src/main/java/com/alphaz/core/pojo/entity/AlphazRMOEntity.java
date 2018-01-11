package com.alphaz.core.pojo.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.entity
 * User: C0dEr
 * Date: 2017/6/13
 * Time: 下午2:39
 * Description:This is a class of com.alphaz.core.pojo.entity
 */
@Entity
@Table(name = "alphaz_rmo", schema = "alphaz", catalog = "")
public class AlphazRMOEntity extends BaseDO {
    private Long rid;
    private Long moid;



    @Basic
    @Column(name = "rid")
    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    @Basic
    @Column(name = "moid")
    public Long getMoid() {
        return moid;
    }

    public void setMoid(Long moid) {
        this.moid = moid;
    }




}
