package com.alphaz.core.pojo.viewmodel.product;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel.product
 * User: C0dEr
 * Date: 2017/7/31
 * Time: 上午10:37
 * Description:This is a class of com.alphaz.core.pojo.viewmodel.product
 */
public class ProductModel {
    public String productname;
    public String producttype;
    public Integer risklevel;

    public Integer getRisklevel() {
        return risklevel;
    }

    public void setRisklevel(Integer risklevel) {
        this.risklevel = risklevel;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }
}
