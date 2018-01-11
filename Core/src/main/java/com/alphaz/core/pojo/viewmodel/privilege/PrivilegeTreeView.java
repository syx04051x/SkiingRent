package com.alphaz.core.pojo.viewmodel.privilege;

import java.util.List;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel.auth
 * User: C0dEr
 * Date: 2017/7/6
 * Time: 上午9:55
 * Description:This is a class of com.alphaz.core.pojo.viewmodel.auth
 */
public class PrivilegeTreeView {

    private String data;
    private String text;
    private StateBean state = new StateBean();
    private List<PrivilegeTreeView> children;
    private String icon;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public List<PrivilegeTreeView> getChildren() {
        return children;
    }

    public void setChildren(List<PrivilegeTreeView> children) {
        this.children = children;
    }

    public static class StateBean {


        private boolean opened = false;
        private boolean disabled = false;
        private boolean selected = false;

        public boolean isOpened() {
            return opened;
        }

        public void setOpened(boolean opened) {
            this.opened = opened;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

    }

}
