package com.alphaz.core.service;


import com.alphaz.core.pojo.DTO.RoleDTO;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.pojo.viewmodel.privilege.AllRoleMenuVIewModel;
import com.alphaz.core.pojo.viewmodel.privilege.MOValueModel;
import com.alphaz.core.pojo.viewmodel.privilege.MenuOperationModel;
import com.alphaz.core.pojo.viewmodel.user.UserViewModel;

import java.util.List;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.service
 * User: C0dEr
 * Date: 2017/7/21
 * Time: 上午10:53
 * Description:This is a class of com.alphaz.core.service
 */
public interface PrivilegeService {
    UserViewModel getUserRole(String username);

    ResponseModel getUserRoleByUsername(String username);

    MenuOperationModel getMenuOperationByUserid(Long userid);

    ResponseModel getMOByUserid(Long userid);

    ResponseModel getMenuByUserId(Long userid);

    ResponseModel getRoles(Long userid);

    ResponseModel deleteRole(Long roleid);

    ResponseModel<AllRoleMenuVIewModel> getAllRoleAndMenuOperation();

    MenuOperationModel getMenuOperationByRoleid(Long roleid);

    ResponseModel getMOByRoleid(Long roleid);

    ResponseModel addRole(String rolename, String description);

    ResponseModel updateRole(RoleDTO role);

    ResponseModel getRoleById(Long roleid);

    ResponseModel updateRMObymoid(Long roleid, List<MOValueModel> map);
}
