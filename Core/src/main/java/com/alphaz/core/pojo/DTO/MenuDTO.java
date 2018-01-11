package com.alphaz.core.pojo.dto;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.dto
 * User: C0dEr
 * Date: 2017/7/7
 * Time: 下午5:11
 * Description:This is a class of com.alphaz.core.pojo.dto
 */
public class MenuDTO {
    public Long id;
    public String menuname;
    public String label;
    public String icon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
