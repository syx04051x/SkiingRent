package com.alphaz.core.dao.base;

import com.alphaz.core.pojo.entity.BaseDO;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.dao
 * User: C0dEr
 * Date: 2017/7/20
 * Time: 下午4:59
 * Description:This is a class of com.alphaz.core.dao
 */
@NoRepositoryBean
public interface BaseRepo<T extends BaseDO, ID extends Serializable> extends JpaRepository<T, ID> {

    HibernateQueryFactory JQF();

    T deleteLogically(ID id);

    List<T> findAllLogically();

    T addLogically(T t);

    List<T> addLogically(List<T> t);
}
