package com.alphaz.core.service.impl;

import com.alphaz.core.constant.DataState;
import com.alphaz.core.dao.SkiingSupportDAO;
import com.alphaz.core.pojo.entity.SkiingSupportEntity;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.service.SkiingSupportService;
import com.alphaz.util.string.DateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SkiingSupportServiceImpl implements SkiingSupportService{

    @Resource
    SkiingSupportDAO skiingSupportDAO;

    @Override
    public ResponseModel search(Integer pageIndex, Integer pageSize, String name) {
        Pageable pageable= new PageRequest(pageIndex-1,pageSize);
        if(name==null||name == ""){
            Page<SkiingSupportEntity> page= skiingSupportDAO.findByState(DataState.Ava,pageable);
            return new ResponseModel(DataState.Ava,"成功！",page);
        }
        Page<SkiingSupportEntity> page= skiingSupportDAO.findByStateAndName(DataState.Ava,pageable,name);
        return new ResponseModel(DataState.Ava,"成功！",page);
    }

    @Override
    public ResponseModel add(SkiingSupportEntity skiingSupportEntity) {
        skiingSupportEntity.setState(DataState.Ava);
        skiingSupportEntity.setCreateTime(DateUtil.getTime());
        skiingSupportDAO.save(skiingSupportEntity);
        return new ResponseModel(DataState.Ava,"成功！",skiingSupportEntity);

    }

    @Override
    public ResponseModel delete(Long id) {
        SkiingSupportEntity finder = skiingSupportDAO.findOne(id);
        finder.setState(DataState.NAva);
        finder.setUpdateTime(DateUtil.getTime());
        skiingSupportDAO.save(finder);
        return new ResponseModel(DataState.Ava,"成功！",finder);
    }

    @Override
    public ResponseModel update(SkiingSupportEntity skiingSupportEntity) {
        SkiingSupportEntity finder = skiingSupportDAO.findOne(skiingSupportEntity.getId());
        finder.setName(skiingSupportEntity.getName());
        finder.setAddress(skiingSupportEntity.getAddress());
        finder.setTel(skiingSupportEntity.getTel());
        finder.setUpdateTime(DateUtil.getTime());
        skiingSupportDAO.save(finder);
        return new ResponseModel(DataState.Ava,"成功！",finder);
    }

    @Override
    public ResponseModel find(Long id) {
        SkiingSupportEntity finder = skiingSupportDAO.findOne(id);
        return new ResponseModel(DataState.Ava,"成功！",finder);
    }
}
