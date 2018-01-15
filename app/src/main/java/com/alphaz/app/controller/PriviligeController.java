package com.alphaz.app.controller;

import com.alphaz.core.pojo.DTO.RoleDTO;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.pojo.viewmodel.privilege.AllRoleMenuVIewModel;
import com.alphaz.core.service.PrivilegeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.controller
 * User: C0dEr
 * Date: 2017/7/5
 * Time: 下午6:12
 * Description:This is a class of com.alphaz.controller
 */
@RequestMapping("privilege")
@RestController
public class PriviligeController {

    @Resource
    PrivilegeService privilegeService;

    /**
     * 获取所有角色列表与权限列表
     *
     * @return
     */
    @GetMapping("rmo")
    public ResponseModel<AllRoleMenuVIewModel> getRMO() {
        return privilegeService.getAllRoleAndMenuOperation();
    }

    /**
     * 获取角色的权限列表
     *
     * @param id 角色id
     * @return
     */
    @GetMapping("rmo/role/{id}")
    public ResponseModel getMOByRole(@PathVariable Long id) {
        return this.privilegeService.getMOByRoleid(id);
    }

    /**
     * 添加新角色
     *
     * @param role 角色信息
     * @return
     */
    @PostMapping("role")
    public ResponseModel addRole(RoleDTO role) {
        return this.privilegeService.addRole(role.rolename, role.description);
    }

    /**
     * 通过角色id获取角色信息
     *
     * @param id 角色ID
     * @return
     */
    @GetMapping("/role/{id}")
    public ResponseModel getRoleById(@PathVariable Long id) {
        return this.privilegeService.getRoleById(id);
    }

    /**
     * 通过角色ID删除角色
     *
     * @param id 角色ID
     * @return
     */
//    @DeleteMapping("/role/{id}")
//    public ResponseModel deleteRoleById(@PathVariable Long id) {
//        return this.privilegeService.deleteRole(id);
//    }

    /**
     * 通过角色id更新角色
     *
     * @param role 角色信息
     * @return
     */
    @PutMapping("role/{id}")
    public ResponseModel updateRole(RoleDTO role) {
        return this.privilegeService.updateRole(role);
    }

    /**
     * 更新角色权限
     *
     * @param id
     * @param ids
     * @return
     */
//    @PutMapping("rmo/moid/{id}")
//    public ResponseModel getRMObymoid(@PathVariable Long id, @RequestBody List<MOValueModel> ids) {
//        return this.privilegeService.updateRMObymoid(id, ids);
//    }
}
