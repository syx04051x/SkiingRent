package com.alphaz.core.pojo.DTO;

import java.util.Date;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.dto
 * User: C0dEr
 * Date: 2017/7/7
 * Time: 下午5:11
 * Description:This is a class of com.alphaz.core.pojo.dto
 */
public class OrderDTO {
    public Long id;
    public Long customId;
    public String customname;
    public String customphone;
    public Long productId;
    public String procuctname;
    public String producttype;
    public Integer position;
    public Integer price;
    public String deposit;
    public Integer day;
    public Date startTime;
    public Date updateTime;
    public Float discount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomId() {
        return customId;
    }

    public void setCustomId(Long customId) {
        this.customId = customId;
    }

    public String getCustomname() {
        return customname;
    }

    public void setCustomname(String customname) {
        this.customname = customname;
    }

    public String getCustomphone() {
        return customphone;
    }

    public void setCustomphone(String customphone) {
        this.customphone = customphone;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProcuctname() {
        return procuctname;
    }

    public void setProcuctname(String procuctname) {
        this.procuctname = procuctname;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public OrderDTO(Long id, Long customId, String customname, String customphone, Long productId, String procuctname, String producttype, Integer position, Integer price, String deposit, Integer day, Date startTime, Date updateTime, Float discount) {
        this.id = id;
        this.customId = customId;
        this.customname = customname;
        this.customphone = customphone;
        this.productId = productId;
        this.procuctname = procuctname;
        this.producttype = producttype;
        this.position = position;
        this.price = price;
        this.deposit = deposit;
        this.day = day;
        this.startTime = startTime;
        this.updateTime = updateTime;
        this.discount = discount;
    }

    public OrderDTO() {
    }
}
