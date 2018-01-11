package com.alphaz.core.service

import com.alphaz.core.pojo.viewmodel.DataTableModel
import com.alphaz.core.pojo.viewmodel.ResponseModel
import com.alphaz.core.pojo.viewmodel.product.FieldViewModel
import com.alphaz.core.pojo.viewmodel.product.ProductListViewModel
import com.alphaz.core.pojo.viewmodel.product.ProductModel
import com.alphaz.core.pojo.viewmodel.product.TemplateViewModel

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.service
 * User: C0dEr
 * Date: 2017/7/31
 * Time: 上午9:47
 * Description:This is a class of com.alphaz.core.service
 */
interface ProductService {
    fun getAllTemplate(): ResponseModel<List<TemplateViewModel>>
    fun getFieldsByTemplatename(templatename: String): ResponseModel<List<FieldViewModel>>
    fun createProducts(model: ProductModel): ResponseModel<Long?>
    fun searchProduct(keyword: String, pageSize: Long, pageIndex: Long, draw: Int): DataTableModel<List<ProductListViewModel>>
}