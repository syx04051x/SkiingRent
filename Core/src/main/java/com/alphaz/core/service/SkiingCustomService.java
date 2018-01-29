package com.alphaz.core.service;

import com.alphaz.core.pojo.entity.SkiingCustomEntity;
import com.alphaz.core.pojo.viewmodel.ResponseModel;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.service
 * User: C0dEr
 * Date: 2016-11-10
 * Time: 14:57
 * Description:
 */
public interface SkiingCustomService {

    ResponseModel search(Integer pageIndex, Integer pageSize, String name);

    ResponseModel add(SkiingCustomEntity skiingCustomEntity);

    ResponseModel delete(Long id);

    ResponseModel update(SkiingCustomEntity skiingCustomEntity);

    ResponseModel find(Long id);

    ResponseModel login(String phone , String password);
}
