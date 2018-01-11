package com.alphaz.core.service.impl

import com.alphaz.core.pojo.entity.QTbFieldEntity
import com.alphaz.core.pojo.entity.QTbProductEntity
import com.alphaz.core.pojo.entity.TbProductEntity
import com.alphaz.core.pojo.viewmodel.DataTableModel
import com.alphaz.core.pojo.viewmodel.ResponseModel
import com.alphaz.core.pojo.viewmodel.product.FieldViewModel
import com.alphaz.core.pojo.viewmodel.product.ProductListViewModel
import com.alphaz.core.pojo.viewmodel.product.ProductModel
import com.alphaz.core.pojo.viewmodel.product.TemplateViewModel
import com.alphaz.core.constant.DataState
import com.alphaz.core.dao.ProductDAO
import com.alphaz.core.service.ProductService
import com.querydsl.core.BooleanBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.service.impl
 * User: C0dEr
 * Date: 2017/7/31
 * Time: 上午9:47
 * Description:This is a class of com.alphaz.core.service.impl
 */
@Service
@Transactional
open class ProductServiceImpl : ProductService {
    @Resource
    lateinit var productDAO: ProductDAO

    override fun getAllTemplate(): ResponseModel<List<TemplateViewModel>> {
        return ResponseModel<List<TemplateViewModel>>().apply {
            this.state = DataState.Ava
            this.message = "查询成功"
            this.data = productDAO.JQF().select(QTbFieldEntity.tbFieldEntity.templatename).from(QTbFieldEntity.tbFieldEntity).distinct().fetch().map {
                TemplateViewModel().apply {
                    this.template = it
                }
            }
        }
    }

    override fun getFieldsByTemplatename(templatename: String): ResponseModel<List<FieldViewModel>> {
        var result = this.productDAO.JQF().select(QTbFieldEntity.tbFieldEntity)
                .from(QTbFieldEntity.tbFieldEntity)
                .where(QTbFieldEntity.tbFieldEntity.templatename.eq(templatename)).fetch()
                .map {
                    FieldViewModel().apply {
                        this.fileid = it.id
                        this.fieldName = it.attributename
                        this.defaultvalue = it.options
                        this.maxlength = it.maxlength
                        this.minlength = it.minlength

                    }

                }
        return ResponseModel<List<FieldViewModel>>().apply {
            this.state = DataState.Ava
            this.message = "查询成功"
            this.data = result
        }
    }

    override fun createProducts(model: ProductModel): ResponseModel<Long?> {
        var entity = TbProductEntity().apply {
            this.name = model.productname
            this.producttype = model.producttype
            this.risklevel = model.risklevel
        }
        entity = this.productDAO.addLogically(entity);
        return ResponseModel<Long?>().apply {
            this.state = DataState.Ava
            this.message = "新建成功"
            this.data = entity.id
        }
    }

    override fun searchProduct(keyword: String, pageSize: Long, pageIndex: Long, draw: Int): DataTableModel<List<ProductListViewModel>> {
        var be = BooleanBuilder()
        if (!keyword.isNullOrEmpty()) {
            be.and(QTbProductEntity.tbProductEntity.name.like("%$keyword%"))
        }
        var count = this.productDAO.JQF().select(QTbProductEntity.tbProductEntity).from(QTbProductEntity.tbProductEntity)
                .where(be.value).fetchCount()
        var model = DataTableModel<List<ProductListViewModel>>().apply {
            this.draw = draw
            this.data = productDAO.JQF().select(QTbProductEntity.tbProductEntity).from(QTbProductEntity.tbProductEntity)
                    .where(be.value).limit(pageSize).offset(pageIndex).fetch().map {
                ProductListViewModel().apply {
                    this.id = it.id
                    this.name = it.name
                    this.riskLevel = it.risklevel
                    this.type = it.producttype
                }
            }
            this.recordsFiltered = count
            this.recordsTotal = count
        }
        return model
    }


}