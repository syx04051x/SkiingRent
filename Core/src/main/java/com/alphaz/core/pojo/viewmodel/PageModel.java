package com.alphaz.core.pojo.viewmodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel
 * User: C0dEr
 * Date: 2017/7/21
 * Time: 下午1:54
 * Description:This is a class of com.alphaz.core.pojo.viewmodel
 */
@ApiModel(description = "分页传递参数")
public class PageModel {
    @Min(0)
    @NotNull(message = "页面偏移量不能为空")
    @ApiModelProperty(value = "页面偏移量", required = true)
    public Integer start;
    @Min(value = 0, message = "")
    @ApiModelProperty(value = "页面大小", required = true)
    @NotNull(message = "页面大小不能为空")
    public Integer length;

    public Integer draw;

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
