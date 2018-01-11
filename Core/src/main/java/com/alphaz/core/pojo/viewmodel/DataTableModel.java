package com.alphaz.core.pojo.viewmodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel
 * User: C0dEr
 * Date: 2017/7/4
 * Time: 下午3:47
 * Description:This is a class of com.alphaz.core.pojo.viewmodel
 */
@ApiModel(description = "前端datatable数据结构")
public class DataTableModel<T> {
    @ApiModelProperty(value = "防止crsf，由datatable生成", required = true)
    private Integer draw;
    @ApiModelProperty(value = "总共数据条数", required = true)
    private Long recordsTotal;
    @ApiModelProperty(value = "过滤后数据条数", required = true)
    private Long recordsFiltered;
    @ApiModelProperty(value = "数据list", required = true)
    private T data;

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
