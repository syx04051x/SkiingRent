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
@Table(name = "alphaz_user_role", schema = "alphaz", catalog = "")
public class AlphazUserRoleEntity extends BaseDO {
    private Long userid;
    private Long roleid;

    @Basic
    @Column(name = "userid")

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "roleid")
    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }


}
