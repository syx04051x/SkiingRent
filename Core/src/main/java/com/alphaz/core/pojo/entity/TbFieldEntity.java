package com.alphaz.core.pojo.entity;

import javax.persistence.*;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.entity
 * User: C0dEr
 * Date: 2017/7/26
 * Time: 上午11:34
 * Description:This is a class of com.alphaz.core.pojo.entity
 */
@Entity
@Table(name = "tb_field", schema = "alphaz", catalog = "")
public class TbFieldEntity extends BaseDO {
    private String attributename;
    private Long maxlength;
    private Long minlength;
    private String defaultvalue;
    private String comment;
    private Integer sort;
    private String templatename;
    private String options;


    @Basic
    @Column(name = "templatename")
    public String getTemplatename() {
        return templatename;
    }

    public void setTemplatename(String templatename) {
        this.templatename = templatename;
    }

    @Basic
    @Column(name = "attributename")
    public String getAttributename() {
        return attributename;
    }

    public void setAttributename(String attributename) {
        this.attributename = attributename;
    }

    @Basic
    @Column(name = "maxlength")
    public Long getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(Long maxlength) {
        this.maxlength = maxlength;
    }

    @Basic
    @Column(name = "minlength")
    public Long getMinlength() {
        return minlength;
    }

    public void setMinlength(Long minlength) {
        this.minlength = minlength;
    }

    @Basic
    @Column(name = "defaultvalue")
    public String getDefaultvalue() {
        return defaultvalue;
    }

    public void setDefaultvalue(String defaultvalue) {
        this.defaultvalue = defaultvalue;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "options")
    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }


    @Basic
    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


}
