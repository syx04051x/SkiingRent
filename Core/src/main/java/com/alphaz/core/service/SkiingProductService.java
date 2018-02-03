package com.alphaz.core.service;

import com.alphaz.core.pojo.entity.SkiingProductEntity;
import com.alphaz.core.pojo.viewmodel.ResponseModel;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.service
 * User: C0dEr
 * Date: 2016-11-10
 * Time: 14:57
 * Description:
 */
public interface SkiingProductService {

    ResponseModel search(Integer pageIndex, Integer pageSize, String name);

    ResponseModel add(SkiingProductEntity skiingProductEntity);

    ResponseModel delete(Long id);

    ResponseModel update(SkiingProductEntity skiingProductEntity);

    ResponseModel find(Long id);

    ResponseModel findByType(String type);
}
