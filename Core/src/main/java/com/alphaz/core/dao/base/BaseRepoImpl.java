package com.alphaz.core.dao.base;

import com.alphaz.core.pojo.entity.BaseDO;
import com.alphaz.core.constant.DataState;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.dao
 * User: C0dEr
 * Date: 2017/7/20
 * Time: 下午4:59
 * Description:This is a class of com.alphaz.core.dao
 */

public class BaseRepoImpl<T extends BaseDO, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepo<T, ID> {
    private final EntityManager entityManager;

    public BaseRepoImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public BaseRepoImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;

    }

    @Override
    @Transactional
    public HibernateQueryFactory JQF() {
        return new HibernateQueryFactory(entityManager.unwrap(Session.class));
    }

    @Override
    @Transactional
    public T deleteLogically(ID id) {
        T t = this.findOne(id);
        if (t == null) {
            return null;
        }
        t.setState(DataState.NAva);
        return this.save(t);
    }

    @Override
    public List<T> findAllLogically() {
        return this.findAll((root, query, cb) -> cb.equal(root.get("state"), DataState.Ava));
    }

    @Override
    public T addLogically(T t) {
        t.setState(DataState.Ava);
        return this.save(t);
    }

    @Override
    public List<T> addLogically(List<T> t) {
        List<T> result = new ArrayList<>();
        for (T item : t) {
            result.add(this.addLogically(item));
        }
        return result;
    }

}
