package com.alphaz.core.pojo.viewmodel.customer;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel.customer.CustomerListViewModel
 * User: C0dEr
 * Date: 2017/7/10
 * Time: 下午2:25
 * Description:This is a class of com.alphaz.core.pojo.viewmodel.customer.CustomerListViewModel
 */
public class CustomerListViewModel {
    public String name;
    public Integer riskLevel;
    public String preferences;

    public CustomerListViewModel(String name, Integer riskLevel, String preferences) {
        this.name = name;
        this.riskLevel = riskLevel;
        this.preferences = preferences;
    }
}
