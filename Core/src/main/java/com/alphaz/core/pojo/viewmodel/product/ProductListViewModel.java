package com.alphaz.core.pojo.viewmodel.product;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel
 * User: C0dEr
 * Date: 2017/7/10
 * Time: 下午2:25
 * Description:This is a class of com.alphaz.core.pojo.viewmodel
 */
public class ProductListViewModel {
    public String name;
    public Long id;
    public Integer riskLevel;
    public String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
