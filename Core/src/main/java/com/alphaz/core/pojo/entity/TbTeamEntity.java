package com.alphaz.core.pojo.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.entity
 * User: C0dEr
 * Date: 2017/7/5
 * Time: 下午3:58
 * Description:This is a class of com.alphaz.core.pojo.entity
 */
@Entity
@Table(name = "tb_team", schema = "alphaz", catalog = "")
public class TbTeamEntity extends BaseDO {

    private String name;
    private Long parentid;



    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "parentid")
    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }


}
