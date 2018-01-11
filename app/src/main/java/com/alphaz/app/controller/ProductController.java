package com.alphaz.app.controller;

import com.alphaz.core.pojo.viewmodel.DataTableModel;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.pojo.viewmodel.product.ProductListViewModel;
import com.alphaz.core.pojo.viewmodel.product.ProductSearchModel;
import com.alphaz.core.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.app.controller
 * User: C0dEr
 * Date: 2017/7/26
 * Time: 下午2:02
 * Description:This is a class of com.alphaz.app.controller
 */
@RestController
@RequestMapping("product")
@Api(value = "product")
public class ProductController {
    @Resource
    private ProductService productService;

//    @GetMapping("template")
//    @ApiOperation(value = "获取所有产品模板", produces = "application/json")
//    public ResponseModel getAllTemplate() {
//        return this.productService.getAllTemplate();
//    }
//
//    @GetMapping("search")
//    @ApiOperation(value = "获取产品列表", produces = "application/json")
//    public DataTableModel<List<ProductListViewModel>> getProducts(ProductSearchModel model) {
//        return this.productService.searchProduct(model.keyword, model.length, model.start, model.draw);
//    }

}
