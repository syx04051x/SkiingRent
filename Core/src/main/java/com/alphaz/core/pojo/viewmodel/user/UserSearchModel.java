package com.alphaz.core.pojo.viewmodel.user;

import com.alphaz.core.pojo.viewmodel.PageModel;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel.user
 * User: C0dEr
 * Date: 2017/7/21
 * Time: 下午1:37
 * Description:This is a class of com.alphaz.core.pojo.viewmodel.user
 */
public class UserSearchModel extends PageModel {
    public String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
