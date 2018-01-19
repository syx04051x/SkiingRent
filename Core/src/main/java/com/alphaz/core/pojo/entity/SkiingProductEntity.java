package com.alphaz.core.pojo.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.entity
 * User: C0dEr
 * Date: 2017/7/4
 * Time: 上午9:02
 * Description:This is a class of com.alphaz.core.pojo.entity
 */
@Entity
@Table(name = "skiing_product", schema = "skiingrent", catalog = "")
public class SkiingProductEntity extends BaseDO {

    private String name;
    private String type;
    //编码
    private String code;
    private Integer price;
    //租金
    private String deposit;
    //图片路径
    private Integer src;
    //富文本描述
    private String content;
    //1在使用  0未使用
    private Integer isuse;
    //1坏  0好
    private Integer isdefect;
    //缺陷描述
    private String defectstr;



    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    @Basic
    @Column(name = "deposit")
    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }
    @Basic
    @Column(name = "src")
    public Integer getSrc() {
        return src;
    }

    public void setSrc(Integer src) {
        this.src = src;
    }
    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @Basic
    @Column(name = "isuse")
    public Integer getIsuse() {
        return isuse;
    }

    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }
    @Basic
    @Column(name = "isdefect")
    public Integer getIsdefect() {
        return isdefect;
    }

    public void setIsdefect(Integer isdefect) {
        this.isdefect = isdefect;
    }
    @Basic
    @Column(name = "defectstr")
    public String getDefectstr() {
        return defectstr;
    }

    public void setDefectstr(String defectstr) {
        this.defectstr = defectstr;
    }
}
