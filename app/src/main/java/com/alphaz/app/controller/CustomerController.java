package com.alphaz.app.controller;

import com.alphaz.core.pojo.viewmodel.DataTableModel;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.pojo.viewmodel.customer.CustomerListViewModel;
import com.alphaz.core.constant.DataState;
import com.alphaz.core.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.controller
 * User: C0dEr
 * Date: 2017/7/4
 * Time: 上午9:28
 * Description:This is a class of com.alphaz.controller
 */
@RequestMapping("customer")
@RestController
@Api(value = "customer", description = "客户相关接口")
@SuppressWarnings("unchecked")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @GetMapping("list")
    @ApiOperation(value = "获取用户列表", produces = "application/json")
    public DataTableModel<List<CustomerListViewModel>> list(@RequestParam Integer start, @RequestParam Integer length, @RequestParam(required = false) Integer draw) {
        ResponseModel<Page<CustomerListViewModel>> handledModel = customerService.pagation(start / length, length);
        DataTableModel<List<CustomerListViewModel>> model = new DataTableModel<>();
        if (handledModel.state == DataState.NAva) {
            return model;
        }
        model.setDraw(draw);
        model.setRecordsFiltered(handledModel.data.getTotalElements());
        model.setRecordsTotal(handledModel.data.getTotalElements());
        model.setData(handledModel.getData().getContent());
        return model;
    }
}
