package com.alphaz.core.pojo.viewmodel.product;

import com.alphaz.core.pojo.viewmodel.PageModel;


/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel.product
 * User: C0dEr
 * Date: 2017/7/24
 * Time: 下午2:57
 * Description:This is a class of com.alphaz.core.pojo.viewmodel.product
 */
public class ProductSearchModel extends PageModel {
    public String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
