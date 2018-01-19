package com.alphaz.core.service.impl;

import com.alphaz.core.constant.DataState;
import com.alphaz.core.dao.SkiingProductDAO;
import com.alphaz.core.pojo.entity.SkiingProductEntity;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.service.SkiingProductService;
import com.alphaz.util.string.DateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SkiingProductServiceImpl implements SkiingProductService {

    @Resource
    SkiingProductDAO skiingProductDAO;

    @Override
    public ResponseModel search(Integer pageIndex, Integer pageSize, String name) {
        Pageable pageable= new PageRequest(pageIndex-1,pageSize);
        if(name==null||name == ""){
            Page<SkiingProductEntity> page= skiingProductDAO.findByState(DataState.Ava,pageable);
            return new ResponseModel(DataState.Ava,"成功！",page);
        }
        Page<SkiingProductEntity> page= skiingProductDAO.findByStateAndName(DataState.Ava,pageable,name);
        return new ResponseModel(DataState.Ava,"成功！",page);
    }

    @Override
    public ResponseModel add(SkiingProductEntity skiingProductEntity) {
        skiingProductEntity.setState(DataState.Ava);
        skiingProductEntity.setCreateTime(DateUtil.getTime());
        skiingProductDAO.save(skiingProductEntity);
        return new ResponseModel(DataState.Ava,"成功！",skiingProductEntity);

    }

    @Override
    public ResponseModel delete(Long id) {
        SkiingProductEntity finder = skiingProductDAO.findOne(id);
        finder.setState(DataState.NAva);
        finder.setUpdateTime(DateUtil.getTime());
        skiingProductDAO.save(finder);
        return new ResponseModel(DataState.Ava,"成功！",finder);
    }

    @Override
    public ResponseModel update(SkiingProductEntity skiingProductEntity) {
        SkiingProductEntity finder = skiingProductDAO.findOne(skiingProductEntity.getId());
        finder.setName(skiingProductEntity.getName());
        finder.setType(skiingProductEntity.getType());
        finder.setCode(skiingProductEntity.getCode());
        finder.setPrice(skiingProductEntity.getPrice());
        finder.setDeposit(skiingProductEntity.getDeposit());
        finder.setSrc(skiingProductEntity.getSrc());
        finder.setContent(skiingProductEntity.getContent());
        finder.setIsuse(skiingProductEntity.getIsuse());
        finder.setIsdefect(skiingProductEntity.getIsdefect());
        finder.setDefectstr(skiingProductEntity.getDefectstr());
        finder.setUpdateTime(DateUtil.getTime());
        skiingProductDAO.save(finder);
        return new ResponseModel(DataState.Ava,"成功！",finder);
    }

    @Override
    public ResponseModel find(Long id) {
        SkiingProductEntity finder = skiingProductDAO.findOne(id);
        return new ResponseModel(DataState.Ava,"成功！",finder);
    }
}
