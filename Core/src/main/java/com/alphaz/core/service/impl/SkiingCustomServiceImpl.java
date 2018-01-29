package com.alphaz.core.service.impl;

import com.alphaz.core.constant.DataState;
import com.alphaz.core.dao.SkiingCustomDAO;
import com.alphaz.core.pojo.entity.SkiingCustomEntity;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.service.SkiingCustomService;
import com.alphaz.util.string.DateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SkiingCustomServiceImpl implements SkiingCustomService {

    @Resource
    SkiingCustomDAO skiingCustomDAO;

    @Override
    public ResponseModel search(Integer pageIndex, Integer pageSize, String name) {
        Pageable pageable= new PageRequest(pageIndex-1,pageSize);
        if(name==null||name == ""){
            Page<SkiingCustomEntity> page= skiingCustomDAO.findByState(DataState.Ava,pageable);
            return new ResponseModel(DataState.Ava,"成功！",page);
        }
        Page<SkiingCustomEntity> page= skiingCustomDAO.findByStateAndName(DataState.Ava,pageable,name);
        return new ResponseModel(DataState.Ava,"成功！",page);
    }

    @Override
    public ResponseModel add(SkiingCustomEntity skiingCustomEntity) {
        skiingCustomEntity.setState(DataState.Ava);
        skiingCustomEntity.setCreateTime(DateUtil.getTime());
        skiingCustomDAO.save(skiingCustomEntity);
        return new ResponseModel(DataState.Ava,"成功！",skiingCustomEntity);

    }

    @Override
    public ResponseModel delete(Long id) {
        SkiingCustomEntity finder = skiingCustomDAO.findOne(id);
        finder.setState(DataState.NAva);
        finder.setUpdateTime(DateUtil.getTime());
        skiingCustomDAO.save(finder);
        return new ResponseModel(DataState.Ava,"成功！",finder);
    }

    @Override
    public ResponseModel update(SkiingCustomEntity skiingCustomEntity) {
        SkiingCustomEntity finder = skiingCustomDAO.findOne(skiingCustomEntity.getId());
        finder.setName(skiingCustomEntity.getName());
        finder.setAddress(skiingCustomEntity.getAddress());
        finder.setTel(skiingCustomEntity.getTel());
        finder.setPassword(skiingCustomEntity.getPassword());
        finder.setSex(skiingCustomEntity.getSex());
        finder.setCard(skiingCustomEntity.getCard());
        finder.setIsvip(skiingCustomEntity.getIsvip());
        finder.setDiscount(skiingCustomEntity.getDiscount());
        finder.setEmail(skiingCustomEntity.getEmail());
        finder.setUpdateTime(DateUtil.getTime());
        skiingCustomDAO.save(finder);
        return new ResponseModel(DataState.Ava,"成功！",finder);
    }

    @Override
    public ResponseModel find(Long id) {
        SkiingCustomEntity finder = skiingCustomDAO.findOne(id);
        return new ResponseModel(DataState.Ava,"成功！",finder);
    }

    @Override
    public ResponseModel login(String phone, String password) {
        SkiingCustomEntity finder = skiingCustomDAO.findByTelAndPasswordAndState(phone,password,DataState.Ava);
        if(finder==null){
            return new ResponseModel(DataState.NAva,"成功！",finder);
        }
        return new ResponseModel(DataState.Ava,"成功！",finder);
    }
}
