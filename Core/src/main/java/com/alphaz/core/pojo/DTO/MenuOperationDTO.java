package com.alphaz.core.pojo.dto;

import com.alphaz.core.constant.DataState;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.dto
 * User: C0dEr
 * Date: 2017/6/14
 * Time: 上午11:49
 * Description:This is a class of com.alphaz.core.pojo.dto
 */
public class MenuOperationDTO {

    public Long menuid;
    public String menuname;
    public Long operationid;
    public String oprationName;
    public String menuLabel;
    public String operationLabel;
    public Long parentid;
    public String url;
    public String menuIcon;
    public String operationIcon;
    public Integer menuSort;
    public DataState isMenuOpeationEnabled;

    public MenuOperationDTO(Long menuid, String menuname, Long operationid,
                            String oprationName, String menuLabel, String operationLabel,
                            Long parentid, String url, String menuIcon, String operationIcon,
                            Integer menuSort, DataState menuOpeationEnabled) {
        this.menuid = menuid;
        this.menuname = menuname;
        this.operationid = operationid;
        this.oprationName = oprationName;
        this.menuLabel = menuLabel;
        this.operationLabel = operationLabel;
        this.parentid = parentid;
        this.url = url;
        this.menuIcon = menuIcon;
        this.operationIcon = operationIcon;
        this.menuSort = menuSort;
        this.isMenuOpeationEnabled = menuOpeationEnabled;
    }

    public MenuOperationDTO(Long menuid, String menuname, Long operationid,
                            String oprationName, String menuLabel, String operationLabel,
                            Long parentid, String url, String menuIcon, String operationIcon,
                            Integer menuSort) {
        this.menuid = menuid;
        this.menuname = menuname;
        this.operationid = operationid;
        this.oprationName = oprationName;
        this.menuLabel = menuLabel;
        this.operationLabel = operationLabel;
        this.parentid = parentid;
        this.url = url;
        this.menuIcon = menuIcon;
        this.operationIcon = operationIcon;
        this.menuSort = menuSort;
    }

    public DataState getIsMenuOpeationEnabled() {
        return isMenuOpeationEnabled;
    }

    public void setIsMenuOpeationEnabled(DataState isMenuOpeationEnabled) {
        this.isMenuOpeationEnabled = isMenuOpeationEnabled;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getOperationIcon() {
        return operationIcon;
    }

    public void setOperationIcon(String operationIcon) {
        this.operationIcon = operationIcon;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public String getMenuLabel() {
        return menuLabel;
    }

    public void setMenuLabel(String menuLabel) {
        this.menuLabel = menuLabel;
    }

    public String getOperationLabel() {
        return operationLabel;
    }

    public void setOperationLabel(String operationLabel) {
        this.operationLabel = operationLabel;
    }

    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public Long getOperationid() {
        return operationid;
    }

    public void setOperationid(Long operationid) {
        this.operationid = operationid;
    }

    public String getOprationName() {
        return oprationName;
    }

    public void setOprationName(String oprationName) {
        this.oprationName = oprationName;
    }
}
