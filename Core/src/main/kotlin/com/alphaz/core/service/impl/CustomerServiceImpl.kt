package com.alphaz.core.service.impl

import com.alphaz.core.pojo.viewmodel.customer.CustomerListViewModel
import com.alphaz.core.pojo.viewmodel.ResponseModel
import com.alphaz.core.constant.DataState
import com.alphaz.core.dao.CustomerDAO
import com.alphaz.core.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import javax.annotation.Resource
import javax.transaction.Transactional

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.service.impl
 * User: C0dEr
 * Date: 2017/7/4
 * Time: 上午9:38
 * Description:This is a class of com.alphaz.service.impl
 */
@Transactional
@Service
open class CustomerServiceImpl : CustomerService {
    @Resource
    lateinit var customerDAO: CustomerDAO

    override fun pagation(pageIndex: Int, pageSize: Int): ResponseModel<Page<CustomerListViewModel>> {

        val pageable = PageRequest(pageIndex, pageSize)
        val page = customerDAO.findByState(DataState.Ava, pageable)
        val response = ResponseModel<Page<CustomerListViewModel>>()
        response.state = DataState.Ava
        response.message = "查询成功"
        response.data = page
        return response
    }

}