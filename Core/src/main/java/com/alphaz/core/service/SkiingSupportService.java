package com.alphaz.core.service;

import com.alphaz.core.pojo.entity.SkiingSupportEntity;
import com.alphaz.core.pojo.viewmodel.ResponseModel;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.service
 * User: C0dEr
 * Date: 2016-11-10
 * Time: 14:57
 * Description:
 */
public interface SkiingSupportService {

    ResponseModel search(Integer pageIndex,Integer pageSize,String name);

    ResponseModel add(SkiingSupportEntity skiingSupportEntity);

    ResponseModel delete(Long id);

    ResponseModel update(SkiingSupportEntity skiingSupportEntity);

    ResponseModel find(Long id);
}
