package com.alphaz.core.constant;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.constant
 * User: C0dEr
 * Date: 2016-11-10
 * Time: 14:51
 * Description:
 */
public enum DataState {
    Ava(1), NAva(0);

    private int state;

    DataState(int state) {
        this.state = state;
    }

    @JsonValue
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
