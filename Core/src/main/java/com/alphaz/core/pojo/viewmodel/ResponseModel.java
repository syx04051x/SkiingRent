package com.alphaz.core.pojo.viewmodel;

import com.alphaz.core.constant.DataState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ProjectName: YouChi
 * PackageName: com.alphaz.core.entity
 * User: C0dEr
 * Date: 2016-11-04
 * Time: 10:53
 * Description:
 */
@ApiModel(description = "业务对象")
public class ResponseModel<T> {
    @ApiModelProperty(value = "返回状态", required = true)
    public DataState state = DataState.Ava;
    @ApiModelProperty(value = "http状态值", required = true)
    public String httpStatus = "200";
    @ApiModelProperty(value = "消息提示", required = true)
    public String message = "操作失败";
    @ApiModelProperty(value = "返回数据", required = true)
    public T data;

    public ResponseModel(DataState state, String httpStatus, String message, T data) {
        this.state = state;
        this.httpStatus = httpStatus;
        this.message = message;
        this.data = data;
    }

    public ResponseModel(DataState state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public ResponseModel(DataState state, String message) {
        this.state = state;
        this.message = message;
    }

    public ResponseModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataState getState() {
        return state;
    }

    public void setState(DataState state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "state=" + state +
                ", httpStatus='" + httpStatus + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }


}
