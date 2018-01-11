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
@Table(name = "tb_product", schema = "alphaz", catalog = "")
public class TbProductEntity extends BaseDO {

    private String name;
    private Integer risklevel;
    private String producttype;
    @Basic
    @Column(name = "producttype")
    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }



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


}
