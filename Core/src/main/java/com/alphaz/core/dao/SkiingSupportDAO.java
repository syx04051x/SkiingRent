package com.alphaz.core.dao;

import com.alphaz.core.constant.DataState;
import com.alphaz.core.dao.base.BaseRepo;
import com.alphaz.core.pojo.entity.SkiingSupportEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.dao
 * User: C0dEr
 * Date: 2017/7/5
 * Time: 下午4:54
 * Description:This is a class of com.alphaz.core.dao
 */
public interface SkiingSupportDAO extends BaseRepo<SkiingSupportEntity, Long> {

    Page findByState(DataState state, Pageable pageable);

    Page findByStateAndName (DataState state, Pageable pageable,String name);


}
