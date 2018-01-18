package com.alphaz.core.service.impl;

import com.alphaz.core.constant.DataState;
import com.alphaz.core.dao.SkiingEmployeeDAO;
import com.alphaz.core.pojo.entity.SkiingEmployeeEntity;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.service.SkiingEmployeeService;
import com.alphaz.util.string.DateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SkiingEmployeeServiceImpl implements SkiingEmployeeService{

    @Resource
    SkiingEmployeeDAO skiingEmployeeDAO;

    @Override
    public ResponseModel search(Integer pageIndex, Integer pageSize, String name) {
        Pageable pageable= new PageRequest(pageIndex-1,pageSize);
        if(name==null||name == ""){
            Page<SkiingEmployeeEntity> page= skiingEmployeeDAO.findByState(DataState.Ava,pageable);
            return new ResponseModel(DataState.Ava,"成功！",page);
        }
        Page<SkiingEmployeeEntity> page= skiingEmployeeDAO.findByStateAndName(DataState.Ava,pageable,name);
        return new ResponseModel(DataState.Ava,"成功！",page);
    }

    @Override
    public ResponseModel add(SkiingEmployeeEntity skiingEmployeeEntity) {
        skiingEmployeeEntity.setState(DataState.Ava);
        skiingEmployeeEntity.setCreateTime(DateUtil.getTime());
        skiingEmployeeDAO.save(skiingEmployeeEntity);
        return new ResponseModel(DataState.Ava,"成功！",skiingEmployeeEntity);

    }

    @Override
    public ResponseModel delete(Long id) {
        SkiingEmployeeEntity finder = skiingEmployeeDAO.findOne(id);
        finder.setState(DataState.NAva);
        finder.setUpdateTime(DateUtil.getTime());
        skiingEmployeeDAO.save(finder);
        return new ResponseModel(DataState.Ava,"成功！",finder);
    }

    @Override
    public ResponseModel update(SkiingEmployeeEntity skiingEmployeeEntity) {
        SkiingEmployeeEntity finder = skiingEmployeeDAO.findOne(skiingEmployeeEntity.getId());
        finder.setName(skiingEmployeeEntity.getName());
        finder.setAddress(skiingEmployeeEntity.getAddress());
        finder.setTel(skiingEmployeeEntity.getTel());
        finder.setDept(skiingEmployeeEntity.getDept());
        finder.setPassword(skiingEmployeeEntity.getPassword());
        finder.setSex(skiingEmployeeEntity.getSex());
        finder.setUpdateTime(DateUtil.getTime());
        skiingEmployeeDAO.save(finder);
        return new ResponseModel(DataState.Ava,"成功！",finder);
    }

    @Override
    public ResponseModel find(Long id) {
        SkiingEmployeeEntity finder = skiingEmployeeDAO.findOne(id);
        return new ResponseModel(DataState.Ava,"成功！",finder);
    }
}
