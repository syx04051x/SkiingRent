package com.alphaz.core.pojo.dto;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel
 * User: C0dEr
 * Date: 2017/7/5
 * Time: 下午5:41
 * Description:This is a class of com.alphaz.core.pojo.viewmodel
 */
public class RoleDTO {
    public Long id;
    public String rolename;
    public String description;
    public boolean iseditable;

    public boolean getIseditable() {
        return iseditable;
    }

    public void setIseditable(boolean iseditable) {
        this.iseditable = iseditable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoleDTO(Long id, String rolename, String description, boolean iseditable) {
        this.id = id;
        this.rolename = rolename;
        this.description = description;
        this.iseditable = iseditable;
    }

    public RoleDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
