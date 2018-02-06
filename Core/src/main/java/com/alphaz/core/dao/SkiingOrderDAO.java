package com.alphaz.core.dao;

import com.alphaz.core.constant.DataState;
import com.alphaz.core.dao.base.BaseRepo;
import com.alphaz.core.pojo.entity.SkiingOrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.dao
 * User: C0dEr
 * Date: 2017/7/5
 * Time: 下午4:54
 * Description:This is a class of com.alphaz.core.dao
 */
public interface SkiingOrderDAO extends BaseRepo<SkiingOrderEntity, Long> {

    @Query(value = "SELECT new com.alphaz.core.pojo.DTO.OrderDTO( c.id,  a.id, a.name, a.tel, b.id, b.name, b.type, c.position, b.price, b.deposit, c.day,c.startTime,c.updateTime,a.discount)" +
            " FROM SkiingCustomEntity  a , " +
            "SkiingProductEntity  b  , " +
            "SkiingOrderEntity  c  " +
            "WHERE c.state = ?1 " +
            "AND a.id = c.customId " +
            "and b.id = c.productId ",
            countQuery = "select  count(*) from SkiingOrderEntity where state=?1")
    Page findByState(DataState state, Pageable pageable);

    List<SkiingOrderEntity> findByCustomIdAndPositionAndState(long customId, int position, DataState state);

    @Query(value = "SELECT new com.alphaz.core.pojo.DTO.OrderDTO( c.id,  a.id, a.name, a.tel, b.id, b.name, b.type, c.position, b.price, b.deposit, c.day,c.startTime,c.updateTime,a.discount)" +
            " FROM SkiingCustomEntity  a , " +
            "SkiingProductEntity  b  , " +
            "SkiingOrderEntity  c  " +
            "WHERE c.state = ?1 " +
            "AND a.id = c.customId " +
            "and b.id = c.productId " +
            "and c.position = ?2",
            countQuery = "select  count(*) from SkiingOrderEntity where state=?1 and position = ?2")
    Page findByStateAndPosition(DataState state, Pageable pageable, Integer position);


}
