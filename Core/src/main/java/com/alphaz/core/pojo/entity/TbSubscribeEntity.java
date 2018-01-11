package com.alphaz.core.pojo.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.entity
 * User: C0dEr
 * Date: 2017/7/5
 * Time: 下午4:09
 * Description:This is a class of com.alphaz.core.pojo.entity
 */
@Entity
@Table(name = "tb_subscribe", schema = "alphaz", catalog = "")
public class TbSubscribeEntity extends BaseDO {

    private Long productid;
    private Long customerid;
    private Integer amount;



    @Basic
    @Column(name = "productid")
    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    @Basic
    @Column(name = "customerid")
    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    @Basic
    @Column(name = "amount")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


}
