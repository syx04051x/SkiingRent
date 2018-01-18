package com.alphaz.core.service;

import com.alphaz.core.pojo.entity.SkiingEmployeeEntity;
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
public interface SkiingEmployeeService {

    ResponseModel search(Integer pageIndex, Integer pageSize, String name);

    ResponseModel add(SkiingEmployeeEntity skiingEmployeeEntity);

    ResponseModel delete(Long id);

    ResponseModel update(SkiingEmployeeEntity skiingEmployeeEntity);

    ResponseModel find(Long id);
}
