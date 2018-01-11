package com.alphaz.core.pojo.viewmodel.privilege;

import com.alphaz.core.pojo.dto.RoleDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.pojo.viewmodel
 * User: C0dEr
 * Date: 2017/7/5
 * Time: 下午6:00
 * Description:This is a class of com.alphaz.core.pojo.viewmodel
 */
@ApiModel(description = "角色菜单对应viewmodel")
public class AllRoleMenuVIewModel {
    @ApiModelProperty("角色列表")
    public List<RoleDTO> roleList;
    @ApiModelProperty("菜单操作对应model")
    public List<PrivilegeTreeView> menuOperation;


}
