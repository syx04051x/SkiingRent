package com.alphaz.core.pojo.viewmodel.product;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel.product
 * User: C0dEr
 * Date: 2017/7/31
 * Time: 上午10:20
 * Description:This is a class of com.alphaz.core.pojo.viewmodel.product
 */
public class FieldViewModel {
    public Long fileid;
    public String fieldName;
    public Long maxlength;
    public Long minlength;
    public String comment;
    public String defaultvalue;


    public Long getFileid() {
        return fileid;
    }

    public void setFileid(Long fileid) {
        this.fileid = fileid;
    }


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Long getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(Long maxlength) {
        this.maxlength = maxlength;
    }

    public Long getMinlength() {
        return minlength;
    }

    public void setMinlength(Long minlength) {
        this.minlength = minlength;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDefaultvalue() {
        return defaultvalue;
    }

    public void setDefaultvalue(String defaultvalue) {
        this.defaultvalue = defaultvalue;
    }
}
