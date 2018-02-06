package com.alphaz.core.service.impl;

import com.alphaz.core.constant.DataState;
import com.alphaz.core.dao.SkiingOrderDAO;
import com.alphaz.core.dao.SkiingProductDAO;
import com.alphaz.core.pojo.entity.SkiingOrderEntity;
import com.alphaz.core.pojo.entity.SkiingProductEntity;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.service.SkiingOrderService;
import com.alphaz.util.string.DateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class SkiingOrderServiceImpl implements SkiingOrderService {

    @Resource
    SkiingOrderDAO skiingOrderDAO;

    @Resource
    SkiingProductDAO skiingProductDAO;

    @Override
    public ResponseModel search(Integer pageIndex, Integer pageSize, Integer position) {
        Pageable pageable= new PageRequest(pageIndex-1,pageSize);
        if(position==null){
            Page<SkiingOrderEntity> page= skiingOrderDAO.findByState(DataState.Ava,pageable);
            return new ResponseModel(DataState.Ava,"成功！",page);
        }
        Page<SkiingOrderEntity> page= skiingOrderDAO.findByStateAndPosition(DataState.Ava,pageable,position);
        return new ResponseModel(DataState.Ava,"成功！",page);
    }

    @Override
    public ResponseModel add(SkiingOrderEntity skiingOrderEntity) {
        //生成一条订单数据
        skiingOrderEntity.setState(DataState.Ava);
        skiingOrderEntity.setCreateTime(DateUtil.getTime());
        skiingOrderDAO.save(skiingOrderEntity);
        //将订单的产品状态修改为可使用
        SkiingProductEntity skiingProductEntity = skiingProductDAO.findOne(skiingOrderEntity.getProductId());
        skiingProductEntity.setIsuse(1);
        skiingProductEntity.setState(DataState.Ava);
        skiingProductEntity.setCreateTime(DateUtil.getTime());
        skiingProductDAO.save(skiingProductEntity);
        return new ResponseModel(DataState.Ava,"成功！",skiingOrderEntity);

    }

    @Override
    public ResponseModel delete(Long id) {
        SkiingOrderEntity finder = skiingOrderDAO.findOne(id);
        finder.setState(DataState.NAva);
        finder.setUpdateTime(DateUtil.getTime());
        skiingOrderDAO.save(finder);
        return new ResponseModel(DataState.Ava,"成功！",finder);
    }

    @Override
    public ResponseModel update(SkiingOrderEntity skiingOrderEntity) {
        SkiingOrderEntity finder = skiingOrderDAO.findOne(skiingOrderEntity.getId());
        finder.setCustomId(skiingOrderEntity.getCustomId());
        finder.setProductId(skiingOrderEntity.getProductId());
        finder.setDay(skiingOrderEntity.getDay());
        finder.setPrice(skiingOrderEntity.getPrice());
        finder.setDeposit(skiingOrderEntity.getDeposit());
        finder.setPosition(skiingOrderEntity.getPosition());
        finder.setUpdateTime(DateUtil.getTime());
        skiingOrderDAO.save(finder);
        return new ResponseModel(DataState.Ava,"成功！",finder);
    }

    @Override
    public ResponseModel find(Long id) {
        SkiingOrderEntity finder = skiingOrderDAO.findOne(id);
        return new ResponseModel(DataState.Ava,"成功！",finder);
    }

    @Override
    public ResponseModel findByLoginIdAndPosition(long loginId, int position) {
        List<SkiingOrderEntity> clothes = skiingOrderDAO.findByCustomIdAndPositionAndState(loginId, position, DataState.Ava);
        return new ResponseModel(DataState.Ava,"成功！",clothes);
    }

    @Override
    public ResponseModel changePosition(Long id, Integer position) {
        SkiingOrderEntity finder = skiingOrderDAO.findOne(id);
        //该状态为已付款，到达下个状态为在使用
        if(position == 2 ){
            finder.setPosition(3);
            skiingOrderDAO.save(finder);
            return new ResponseModel(DataState.Ava,"成功！",finder);
        }
        //该状态为在使用，到达下个状态为已完成，产品则需要重新修改状态为未使用
        if(position == 3 ){
            finder.setPosition(4);
            skiingOrderDAO.save(finder);
            //将订单的产品状态修改为可使用
            SkiingProductEntity skiingProductEntity = skiingProductDAO.findOne(finder.getProductId());
            skiingProductEntity.setIsuse(0);
            skiingProductEntity.setState(DataState.Ava);
            skiingProductEntity.setCreateTime(DateUtil.getTime());
            skiingProductDAO.save(skiingProductEntity);

        }
        return new ResponseModel(DataState.Ava,"成功！",finder);


    }

}
