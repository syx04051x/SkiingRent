package com.alphaz.core.pojo.viewmodel.privilege;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel
 * User: C0dEr
 * Date: 2017/7/4
 * Time: 下午4:25
 * Description:This is a class of com.alphaz.core.pojo.viewmodel
 */
public class MenuViewModel {
    private Long id;
    private MenuViewModel child;
    private String menuName;
    private String icon;
    private String url;
    private String label;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public MenuViewModel getChild() {
        return child;
    }

    public void setChild(MenuViewModel child) {
        this.child = child;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
