package com.alphaz.core.pojo.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.entity
 * User: C0dEr
 * Date: 2017/7/5
 * Time: 上午10:52
 * Description:This is a class of com.alphaz.core.pojo.entity
 */
@Entity
@Table(name = "tb_investmentmodel", schema = "alphaz", catalog = "")
public class TbInvestmentmodelEntity extends BaseDO {
    private String name;
    private Long assettypeid;
    private Double investmentrate;
    private Long combinationid;



    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "assettypeid")
    public Long getAssettypeid() {
        return assettypeid;
    }

    public void setAssettypeid(Long assettypeid) {
        this.assettypeid = assettypeid;
    }

    @Basic
    @Column(name = "investmentrate")
    public Double getInvestmentrate() {
        return investmentrate;
    }

    public void setInvestmentrate(Double investmentrate) {
        this.investmentrate = investmentrate;
    }

    @Basic
    @Column(name = "combinationid")
    public Long getCombinationid() {
        return combinationid;
    }

    public void setCombinationid(Long combinationid) {
        this.combinationid = combinationid;
    }


}
