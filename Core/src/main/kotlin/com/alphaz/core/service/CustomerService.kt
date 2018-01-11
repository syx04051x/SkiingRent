package com.alphaz.core.service

import com.alphaz.core.pojo.viewmodel.customer.CustomerListViewModel
import com.alphaz.core.pojo.viewmodel.ResponseModel
import org.springframework.data.domain.Page

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.service
 * User: C0dEr
 * Date: 2017/7/4
 * Time: 上午9:38
 * Description:This is a class of com.alphaz.service
 */
interface CustomerService {
    fun pagation(pageIndex: Int, pageSize: Int): ResponseModel<Page<CustomerListViewModel>>
}