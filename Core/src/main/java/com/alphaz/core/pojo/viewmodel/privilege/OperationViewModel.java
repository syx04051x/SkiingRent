package com.alphaz.core.pojo.viewmodel.privilege;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel
 * User: C0dEr
 * Date: 2017/7/6
 * Time: 上午9:23
 * Description:This is a class of com.alphaz.core.pojo.viewmodel
 */
public class OperationViewModel {
    public Long id;
    public String label;
    public String operationName;

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

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
