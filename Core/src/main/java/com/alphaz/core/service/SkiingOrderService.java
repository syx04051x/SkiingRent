package com.alphaz.core.service;

import com.alphaz.core.pojo.entity.SkiingOrderEntity;
import com.alphaz.core.pojo.viewmodel.ResponseModel;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.service
 * User: C0dEr
 * Date: 2016-11-10
 * Time: 14:57
 * Description:
 */
public interface SkiingOrderService {

    ResponseModel search(Integer pageIndex, Integer pageSize, Integer position);

    ResponseModel add(SkiingOrderEntity skiingOrderEntity);

    ResponseModel delete(Long id);

    ResponseModel cancelOrder(Long id);

    ResponseModel update(SkiingOrderEntity skiingOrderEntity);

    ResponseModel find(Long id);

    ResponseModel findByLoginIdAndPosition(long loginId, int position);

    ResponseModel findByLoginIdAndPositionIn(long loginId);

    ResponseModel changePosition(Long id, Integer position);
}
