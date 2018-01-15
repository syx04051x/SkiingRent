package com.alphaz.core.service.impl;

import com.alphaz.core.constant.DataState;
import com.alphaz.core.dao.RMODAO;
import com.alphaz.core.dao.RoleDAO;
import com.alphaz.core.dao.UserDAO;
import com.alphaz.core.pojo.DTO.*;
import com.alphaz.core.pojo.entity.*;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.pojo.viewmodel.privilege.*;
import com.alphaz.core.pojo.viewmodel.user.UserViewModel;
import com.alphaz.core.service.LocalizationService;
import com.alphaz.core.service.PrivilegeService;
import com.alphaz.util.extension.StreamPredicate;
import com.alphaz.util.valid.ValideHelper;
import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.service.impl
 * User: C0dEr
 * Date: 2017/7/21
 * Time: 上午10:53
 * Description:This is a class of com.alphaz.core.service.impl
 */
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0")
@Transactional
@Service
public class PrivilegeServiceImpl implements PrivilegeService {
    @Resource
    private UserDAO userDAO;
    @Resource
    private RMODAO rmodao;
    @Resource
    private RoleDAO roleDAO;
    @Resource
    private LocalizationService localizationService;

    /**
     * 用于权限认证获取当前用户的角色
     *
     * @param username 账户名
     * @return
     */
    @Override
    public UserViewModel getUserRole(String username) {
        List<UserRoleDTO> userRoleDTO = this.userDAO.getUserRole(username);
        UserViewModel userViewModel = new UserViewModel();
        if (ValideHelper.isNullOrEmpty(userRoleDTO)) {
            return null;
        }
        userViewModel.setUsername(userRoleDTO.get(0).getUsername());
        userViewModel.setUserid(userRoleDTO.get(0).getUserid());
        userViewModel.setPassword(userRoleDTO.get(0).getPassword());
        Map<Long, String> roles = new HashMap<>();
        userRoleDTO.forEach(a -> roles.put(a.getRoleid(), a.getRolename()));
        userViewModel.setRoles(roles);
        return userViewModel;
    }

    @Override
    public ResponseModel getUserRoleByUsername(String username) {
        return new ResponseModel<>(DataState.Ava, localizationService.getMessage("fetchSuccess"), this.getUserRole(username));
    }

    @Override
    public MenuOperationModel getMenuOperationByUserid(Long userid) {
        List<MenuOperationDTO> mo = this.userDAO.getMenuOperationByUserid(userid);
        return MOTree(mo);
    }

    @Override
    public MenuOperationModel getMenuOperationByRoleid(Long roleid) {
        List<MenuOperationDTO> mo = this.userDAO.getMenuOperationByRoleid(roleid);
        return MOTree(mo);
    }

    @Override
    public ResponseModel getMOByUserid(Long userid) {
        return new ResponseModel<>(DataState.Ava, localizationService.getMessage("fetchSuccess"), this.getMenuOperationByUserid(userid));
    }

    @Override
    public ResponseModel getMenuByUserId(Long userid) {
        List<MenuOperationDTO> dto = this.userDAO.getMenuOperationByUserid(userid);
        //由于getMenuOperationByUserid 方法结果是所有operation和menu对应数据，存在重复menu，这里只需要唯一menu
        System.out.println(dto);
        List<MenuOperationDTO> sorted = dto.stream().filter(StreamPredicate.distinctByKey(p -> p.menuid))
                .sorted(Comparator.comparing(a -> a.menuSort)).collect(Collectors.toList());
        List<MenuViewModel> menuViewModels = new ArrayList<>();
        sorted.forEach(a -> {
            MenuViewModel mvm = new MenuViewModel();
            mvm.setMenuName(a.menuname);
            mvm.setLabel(a.menuLabel);
            mvm.setIcon(a.menuIcon);
            mvm.setUrl(a.url);
            if (a.parentid != null) {
                Optional<MenuOperationDTO> menuOperationDTO = sorted.stream().filter(b -> a.parentid.equals(b.menuid)).findFirst();
                menuOperationDTO.ifPresent(menuOperationDTO1 -> mvm.setChild(new MenuViewModel() {{
                    setUrl(menuOperationDTO1.url);
                    setIcon(menuOperationDTO1.menuIcon);
                    setMenuName(menuOperationDTO1.menuname);
                    setLabel(menuOperationDTO1.menuLabel);
                }}));
            }
            menuViewModels.add(mvm);
        });
        ResponseModel model = new ResponseModel();
        model.state = DataState.Ava;
        model.setMessage(localizationService.getMessage("fetchSuccess"));
        model.data = menuViewModels;
        return model;
    }

    @Override
    public ResponseModel getRoles(Long userid) {
        List<RoleDTO> rolelist = userDAO.findRolesByUserid(userid);
        ResponseModel<List<RoleDTO>> model = new ResponseModel<>();
        model.state = DataState.Ava;
        model.message = localizationService.getMessage("fetchSuccess");
        model.data = rolelist;
        return model;
    }

    @Override
    @Transactional
    public ResponseModel deleteRole(Long roleid) {
        this.roleDAO.JQF().update(QAlphazRoleEntity.alphazRoleEntity).where(QAlphazRoleEntity.alphazRoleEntity.id.eq(roleid)).set(QAlphazRoleEntity.alphazRoleEntity.state, DataState.NAva).execute();
        this.rmodao.deleteAlphazRMOEntitiesByRid(roleid);
        ResponseModel model = new ResponseModel<>();
        model.state = DataState.Ava;
        model.message = localizationService.getMessage("deleteSuccess");
        return model;
    }

    @Override
    public ResponseModel<AllRoleMenuVIewModel> getAllRoleAndMenuOperation() {
        List<MenuOperationDTO> dto = this.userDAO.getALLMenuOperation();
        MenuOperationModel menuOperationModel = MOTree(dto);
        AllRoleMenuVIewModel data = new AllRoleMenuVIewModel();

        List<PrivilegeTreeView> list = new ArrayList<>();
        menuOperationModel.keyPair.forEach((key, value) -> list.add(new PrivilegeTreeView() {{
            setText(key.getLabel());
            setData(key.getId().toString());
            setIcon(key.getIcon());
            setChildren(value.stream().map(b -> new PrivilegeTreeView() {{
                setData(key.getId() + "_" + b.getId());
                setText(b.getLabel());
                setIcon(b.getIcon());
            }}).collect(Collectors.toList()));
        }}));
        data.menuOperation = list;
        data.roleList = this.userDAO.findALLRolesByState(DataState.Ava);
        ResponseModel model = new ResponseModel();
        model.message = localizationService.getMessage("fetchSuccess");
        model.state = DataState.Ava;
        model.data = data;
        return model;
    }

    @Override
    public ResponseModel getMOByRoleid(Long roleid) {
        MenuOperationModel moModel = this.getMenuOperationByRoleid(roleid);
        AlphazRoleEntity role = this.roleDAO.findOne(roleid);
        List<PrivilegeTreeView> list = new ArrayList<>();
        moModel.keyPair.forEach((key, value) -> list.add(new PrivilegeTreeView() {{
            setText(key.getLabel());
            setData(key.getId().toString());
            setIcon(key.getIcon());
            setState(new StateBean() {{
                setOpened(true);
                setDisabled(role.getIseditable());
            }});
            setChildren(value.stream().map(b -> new PrivilegeTreeView() {{
                setData(key.getId() + "_" + b.getId());
                setText(b.getLabel());
                setIcon(b.getIcon());
                setState(new StateBean() {{
                    setOpened(true);
                    setSelected(b.getIsenabled() == DataState.NAva);
                    setDisabled(role.getIseditable());
                }});
            }}).collect(Collectors.toList()));
        }}));
        ResponseModel model = new ResponseModel();
        model.message = localizationService.getMessage("fetchSuccess");
        model.state = DataState.Ava;
        model.data = list;
        return model;
    }

    @Override
    public ResponseModel addRole(String rolename, String description) {
        AlphazRoleEntity entity = new AlphazRoleEntity();
        entity.setLabel(rolename);
        entity.setDescription(description);
        entity.setState(DataState.Ava);
        entity.setIseditable(true);
        AlphazRoleEntity role = roleDAO.save(entity);
        RoleDTO dto = new RoleDTO(role.getId(), role.getLabel(), role.getDescription(), role.getIseditable());
        ResponseModel model = new ResponseModel();
        model.state = DataState.Ava;
        model.message = localizationService.getMessage("fetchSuccess");
        model.data = dto;
        return model;
    }

    @Override
    public ResponseModel updateRole(RoleDTO role) {
        AlphazRoleEntity alphazRoleEntity = this.roleDAO.findOne(role.getId());
        alphazRoleEntity.setLabel(role.getRolename());
        alphazRoleEntity.setDescription(role.getDescription());
        this.roleDAO.save(alphazRoleEntity);
        ResponseModel model = new ResponseModel();
        model.state = DataState.Ava;
        model.message = localizationService.getMessage("updateSuccess");
        model.data = role;
        return model;
    }

    @Override
    public ResponseModel getRoleById(Long roleid) {
        AlphazRoleEntity role = this.roleDAO.findOne(roleid);
        RoleDTO roleDTO = new RoleDTO(role.getId(), role.getLabel(), role.getDescription(), role.getIseditable());
        ResponseModel model = new ResponseModel();
        model.message = localizationService.getMessage("fetchSuccess");
        model.state = DataState.Ava;
        model.data = roleDTO;
        return model;
    }

    /**
     * dto转化成树形结构
     *
     * @param mo
     * @return
     */
    private MenuOperationModel MOTree(List<MenuOperationDTO> mo) {
        MenuOperationModel model = new MenuOperationModel();
        Map<String, List<String>> namePair = new HashMap<>();
        Map<MenuDTO, List<OperationDTO>> keyPair = new HashMap<>();
        List<Long> menuids = mo.stream().map(MenuOperationDTO::getMenuid).distinct().collect(Collectors.toList());
        menuids.forEach(a -> {
            List<MenuOperationDTO> modto = mo.stream().filter(b -> a == b.getMenuid()).collect(Collectors.toList());
            if (!ValideHelper.isNullOrEmpty(modto)) {
                namePair.put(modto.get(0).getMenuname(), modto.stream().map(MenuOperationDTO::getOprationName).collect(Collectors.toList()));
                keyPair.put(new MenuDTO() {{
                    setId(modto.get(0).getMenuid());
                    setMenuname(modto.get(0).getMenuname());
                    setLabel(modto.get(0).getMenuLabel());
                    setIcon(modto.get(0).getMenuIcon());
                }}, modto.stream().map(b -> new OperationDTO() {{
                    setId(b.getOperationid());
                    setOperationname(b.getOprationName());
                    setLabel(b.getOperationLabel());
                    setIcon(b.getOperationIcon());
                    setIsenabled(b.getIsMenuOpeationEnabled());
                }}).collect(Collectors.toList()));
            }
        });
        model.setKeyPair(keyPair);
        model.setNamePair(namePair);
        return model;
    }

    @Override
    public ResponseModel updateRMObymoid(Long roleid, List<MOValueModel> maps) {
        QAlphazMenuOperationEntity qAlphazMenuOperationEntity = QAlphazMenuOperationEntity.alphazMenuOperationEntity;
        QAlphazRMOEntity qAlphazRMOEntity = QAlphazRMOEntity.alphazRMOEntity;
        BooleanBuilder bb = new BooleanBuilder();
        if (!ValideHelper.isNullOrEmpty(maps)) {
            maps.forEach(a -> bb.or(qAlphazMenuOperationEntity.menuid.eq(a.getMenuid()).and(qAlphazMenuOperationEntity.operationid.eq(a.getOperationid()))));
        }
        //已经存在数据库的角色对应权限
        List<RoleMODTO> staticMo = this.userDAO.JQF().select(qAlphazRMOEntity.id,
                qAlphazMenuOperationEntity.menuid,
                qAlphazMenuOperationEntity.operationid,
                qAlphazRMOEntity.state,
                qAlphazRMOEntity.moid,
                qAlphazRMOEntity.rid)
                .from(qAlphazRMOEntity).from(qAlphazMenuOperationEntity).where(bb.getValue())
                .where(qAlphazRMOEntity.moid.eq(qAlphazMenuOperationEntity.id)
                        .and(qAlphazRMOEntity.rid.eq(roleid))).fetch().stream()
                .map(a ->
                        new RoleMODTO(a.get(qAlphazRMOEntity.id),
                                a.get(qAlphazMenuOperationEntity.menuid),
                                a.get(qAlphazMenuOperationEntity.operationid),
                                a.get(qAlphazRMOEntity.moid),
                                a.get(qAlphazRMOEntity.rid),
                                a.get(qAlphazRMOEntity.state)))
                .collect(Collectors.toList());
        //需要存储的角色对应权限，还未在数据库中建立
        List<MOValueModel> needToInsert = maps.stream().
                filter(a -> staticMo.stream().noneMatch(b -> b.getMenuid().equals(a.getMenuid()) && b.getOperationid().equals(a.getOperationid())))
                .collect(Collectors.toList());
        if (needToInsert.size() > 0) {
            BooleanBuilder newbb = new BooleanBuilder();
            needToInsert.forEach(a -> newbb.or(qAlphazMenuOperationEntity.menuid.eq(a.getMenuid()).and(qAlphazMenuOperationEntity.operationid.eq(a.getOperationid()))));
            List<AlphazMenuOperationEntity> alphazMenuOperationEntities = this.userDAO.JQF().select(qAlphazMenuOperationEntity)
                    .from(qAlphazMenuOperationEntity)
                    .where(newbb)
                    .fetch();
//保存新增的角色对应权限
            rmodao.save(alphazMenuOperationEntities.stream().map(b -> {
                AlphazRMOEntity rmoEntity = new AlphazRMOEntity();
                needToInsert.stream().filter(a -> b.getOperationid().equals(a.getOperationid()) && b.getMenuid().equals(a.getMenuid()))
                        .findFirst().ifPresent(c -> {
                    rmoEntity.setState(c.getState());
                    rmoEntity.setRid(roleid);
                    rmoEntity.setMoid(b.getId());
                });
                return rmoEntity;
            }).collect(Collectors.toList()));
        }
        //设置已经有的权限对应角色状态
        maps.forEach(b ->
                staticMo.stream().filter(a -> b.getOperationid().equals(a.getOperationid()) && b.getMenuid().equals(a.getMenuid()))
                        .findFirst()
                        .ifPresent(c -> c.setState(b.getState()))
        );
        //更新已经有的权限对应角色状态
        rmodao.save(staticMo.stream().map(model -> {
            AlphazRMOEntity alphazRMOEntity = new AlphazRMOEntity();
            alphazRMOEntity.setId(model.getId());
            alphazRMOEntity.setMoid(model.getMoid());
            alphazRMOEntity.setRid(model.getRid());
            alphazRMOEntity.setState(model.getState());
            return alphazRMOEntity;
        }).collect(Collectors.toList()));
        ResponseModel model = new ResponseModel();
        model.message = localizationService.getMessage("updateSuccess");
        model.state = DataState.Ava;
        return model;
    }
}
