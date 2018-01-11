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
@Table(name = "tb_teamusers", schema = "alphaz", catalog = "")
public class TbTeamusersEntity extends BaseDO {

    private Long teamid;
    private Long userid;



    @Basic
    @Column(name = "teamid")
    public Long getTeamid() {
        return teamid;
    }

    public void setTeamid(Long teamid) {
        this.teamid = teamid;
    }

    @Basic
    @Column(name = "userid")
    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }


}
