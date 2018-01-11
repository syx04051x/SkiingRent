package com.alphaz.core.dao;

import com.alphaz.core.pojo.entity.AlphazRMOEntity;
import com.alphaz.core.dao.base.BaseRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.dao
 * User: C0dEr
 * Date: 2017/7/10
 * Time: 下午6:11
 * Description:This is a class of com.alphaz.core.dao
 */
public interface RMODAO extends BaseRepo<AlphazRMOEntity, Long> {

    @Modifying
    @Transactional
    void deleteAlphazRMOEntitiesByRid(Long rid);
}
