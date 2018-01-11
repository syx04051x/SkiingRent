package com.alphaz.core.pojo.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.entity
 * User: C0dEr
 * Date: 2017/7/4
 * Time: 上午9:02
 * Description:This is a class of com.alphaz.core.pojo.entity
 */
@Entity
@Table(name = "tb_customer", schema = "alphaz", catalog = "")
public class TbCustomerEntity extends BaseDO {
    private String name;
    private Integer risklevel;
    private String preference;



    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "risklevel")
    public Integer getRisklevel() {
        return risklevel;
    }

    public void setRisklevel(Integer risklevel) {
        this.risklevel = risklevel;
    }

    @Basic
    @Column(name = "preference")
    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }


}
