package com.alphaz.core.service.impl;

import com.alphaz.core.constant.DataState;
import com.alphaz.core.dao.SkiingOrderDAO;
import com.alphaz.core.pojo.entity.SkiingOrderEntity;
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

    @Override
    public ResponseModel search(Integer pageIndex, Integer pageSize, String name) {
        Pageable pageable= new PageRequest(pageIndex-1,pageSize);
        if(name==null||name == ""){
            Page<SkiingOrderEntity> page= skiingOrderDAO.findByState(DataState.Ava,pageable);
            return new ResponseModel(DataState.Ava,"成功！",page);
        }
//        Page<SkiingOrderEntity> page= skiingOrderDAO.findByStateAndName(DataState.Ava,pageable,name);
        return new ResponseModel(DataState.Ava,"成功！","");
    }

    @Override
    public ResponseModel add(SkiingOrderEntity skiingOrderEntity) {
        skiingOrderEntity.setState(DataState.Ava);
        skiingOrderEntity.setCreateTime(DateUtil.getTime());
        skiingOrderDAO.save(skiingOrderEntity);
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
    public ResponseModel findByLoginId(long loginId) {
        List<SkiingOrderEntity> clothes = skiingOrderDAO.findByCustomId(loginId);
        return new ResponseModel(DataState.Ava,"成功！",clothes);
    }
}
