package com.alphaz.core.pojo.viewmodel.privilege;

import com.alphaz.core.constant.DataState;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel.auth
 * User: C0dEr
 * Date: 2017/7/7
 * Time: 下午6:31
 * Description:This is a class of com.alphaz.core.pojo.viewmodel.auth
 */
public class MOValueModel {
    public Long menuid;
    public Long operationid;
    public DataState state;

    public DataState getState() {
        return state;
    }

    public void setState(DataState state) {
        this.state = state;
    }

    public Long getOperationid() {
        return operationid;
    }

    public void setOperationid(Long operationid) {
        this.operationid = operationid;
    }

    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }
}
