package com.alphaz.core.dao;

import com.alphaz.core.pojo.entity.TbCustomerEntity;
import com.alphaz.core.pojo.viewmodel.customer.CustomerListViewModel;
import com.alphaz.core.constant.DataState;
import com.alphaz.core.dao.base.BaseRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.dao
 * User: C0dEr
 * Date: 2017/7/4
 * Time: 上午9:30
 * Description:This is a class of com.alphaz.core.dao
 */
public interface CustomerDAO extends BaseRepo<TbCustomerEntity, Long> {
    @Query(value = "select  new com.alphaz.core.pojo.viewmodel.customer.CustomerListViewModel(name,risklevel,preference) from TbCustomerEntity where state=?1",
            countQuery = "select  count(*) from TbCustomerEntity where state=?1")
    Page<CustomerListViewModel> findByState(DataState state, Pageable pageable);
}
