package com.alphaz.core.dao;

import com.alphaz.core.constant.DataState;
import com.alphaz.core.dao.base.BaseRepo;
import com.alphaz.core.pojo.DTO.MenuOperationDTO;
import com.alphaz.core.pojo.DTO.RoleDTO;
import com.alphaz.core.pojo.DTO.UserRoleDTO;
import com.alphaz.core.pojo.entity.AlphazUserEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Created by admin on 2017/1/13.
 */

public interface UserDAO extends BaseRepo<AlphazUserEntity, Long> {

    AlphazUserEntity findFirstByUsernameAndPasswordAndState(String username, String password, DataState state);

    @Query(value = "SELECT new com.alphaz.core.pojo.DTO.UserRoleDTO(a.id , a.username, c.id , c.rolename,a.password)" +
            " FROM AlphazUserEntity  a , AlphazUserRoleEntity  b  , AlphazRoleEntity  c  " +
            "WHERE a.state = 0 AND c.state = 0 and a.username=?1 and a.id=b.userid and b.roleid=c.id")
    List<UserRoleDTO> getUserRole(String username);

    @Query(value = "SELECT new com.alphaz.core.pojo.DTO.UserRoleDTO(a.id , a.username, c.id , c.rolename,a.password)" +
            " FROM AlphazUserEntity  a , AlphazUserRoleEntity  b  , AlphazRoleEntity  c  " +
            "WHERE a.state = 0 AND c.state = 0 and a.id=?1 and a.id=b.userid and b.roleid=c.id")
    List<UserRoleDTO> getUserRole(Long userid);

    @Query("SELECT  new com.alphaz.core.pojo.DTO.MenuOperationDTO(b.id,b.menuname, c.id,c.operationname,b.label,c.label,b.parentid,b.url,b.icon,c.icon,b.sort,e.state) " +
            "FROM AlphazRoleEntity a , AlphazUserRoleEntity f , AlphazMenuEntity b , AlphazOperationEntity c , AlphazMenuOperationEntity d , AlphazRMOEntity e ,AlphazUserEntity  g " +
            "WHERE a.id = f.roleid" +
            " AND a.id = e.rid " +
            "AND b.id = d.menuid " +
            "and d.operationid = c.id " +
            "and e.moid = d.id " +
            "and g.id = f.userid " +
            "and a.state = 0 " +
            "and b.state = 0 " +
            "and c.state = 0 " +
            "and g.id =?1")
    List<MenuOperationDTO> getMenuOperationByUserid(Long userid);

    @Query("SELECT  new com.alphaz.core.pojo.DTO.MenuOperationDTO(b.id,b.menuname, c.id,c.operationname,b.label,c.label,b.parentid,b.url,b.icon,c.icon,b.sort,e.state) " +
            "FROM AlphazRoleEntity a ," +
            "AlphazMenuEntity b ," +
            " AlphazOperationEntity c , " +
            "AlphazMenuOperationEntity d ," +
            " AlphazRMOEntity e " +
            " where a.id = e.rid " +
            "AND b.id = d.menuid " +
            "and d.operationid = c.id " +
            "and e.moid = d.id " +
            "and a.state = 0 " +
            "and b.state = 0 " +
            "and c.state = 0 " +
            "and a.id =?1")
    List<MenuOperationDTO> getMenuOperationByRoleid(Long roleid);

    @Query("SELECT DISTINCT new com.alphaz.core.pojo.DTO.MenuOperationDTO(b.id,b.menuname, c.id,c.operationname,b.label,c.label,b.parentid,b.url,b.icon,c.icon,b.sort) " +
            "FROM  AlphazMenuEntity b , AlphazOperationEntity c , AlphazMenuOperationEntity d " +
            "where b.id = d.menuid " +
            "and d.operationid = c.id " +
            "and b.state = 0 " +
            "and c.state = 0 "
    )
    List<MenuOperationDTO> getALLMenuOperation();

    @Query("SELECT DISTINCT new com.alphaz.core.pojo.DTO.RoleDTO(a.id,a.label,a.description,a.iseditable) " +
            "FROM AlphazRoleEntity a , AlphazUserRoleEntity f , AlphazMenuEntity b   ,AlphazUserEntity  g " +
            "WHERE a.id = f.roleid " +
            "and g.id = f.userid " +
            "and a.state = 0 " +
            "and b.state = 0 " +
            "and g.id =?1")
    List<RoleDTO> findRolesByUserid(Long userid);

    @Query("SELECT DISTINCT new com.alphaz.core.pojo.DTO.RoleDTO(a.id,a.label,a.description,a.iseditable) " +
            "FROM AlphazRoleEntity a " +
            "where a.state =?1")
    List<RoleDTO> findALLRolesByState(DataState state);

   AlphazUserEntity findByUsername(String username);
}
