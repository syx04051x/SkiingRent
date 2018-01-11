package com.alphaz.core.pojo.dto;

import com.alphaz.core.constant.DataState;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.dto
 * User: C0dEr
 * Date: 2017/7/11
 * Time: 上午10:15
 * Description:This is a class of com.alphaz.core.pojo.dto
 */
public class RoleMODTO {
    public Long id;
    public Long menuid;
    public Long operationid;
    public Long moid;
    public Long rid;
    public DataState state;

    public RoleMODTO(Long id, Long menuid, Long operationid, Long moid, Long rid, DataState state) {
        this.id = id;
        this.menuid = menuid;
        this.operationid = operationid;
        this.moid = moid;
        this.rid = rid;
        this.state = state;
    }

    public Long getMoid() {
        return moid;
    }

    public void setMoid(Long moid) {
        this.moid = moid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public DataState getState() {
        return state;
    }

    public void setState(DataState state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    public Long getOperationid() {
        return operationid;
    }

    public void setOperationid(Long operationid) {
        this.operationid = operationid;
    }
}
