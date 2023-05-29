package com.wyd.satokendemospringboot.demos.service.impl;

import com.wyd.satokendemospringboot.demos.common.ex.DBException;
import com.wyd.satokendemospringboot.demos.entity.Permission;
import com.wyd.satokendemospringboot.demos.dao.PermissionDao;
import com.wyd.satokendemospringboot.demos.entity.po.PermissionPo;
import com.wyd.satokendemospringboot.demos.myenum.OpsEnum;
import com.wyd.satokendemospringboot.demos.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.ValidationUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 不同角色对不同业务有默认的权限，如果对某个账户设置了权限，则按照账户权限为准(Permission)表服务实现类
 *
 * @author makejava
 * @since 2023-05-22 17:49:02
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Permission queryById(Long id) {
        return this.permissionDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param permission 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Permission> queryByPage(Permission permission, PageRequest pageRequest) {
        long total = this.permissionDao.count(permission);
        return new PageImpl<>(this.permissionDao.queryAllByLimit(permission, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission insert(Permission permission) {
        this.permissionDao.insert(permission);
        return permission;
    }

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission update(Permission permission) {
        this.permissionDao.update(permission);
        return this.queryById(permission.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.permissionDao.deleteById(id) > 0;
    }

    private PermissionPo permissionToPo(Permission permission){
        String permissions = permission.getPermissions();
        List<String> ops = new ArrayList<>();
        if (permissions==null || permissions.isEmpty()) throw new DBException("permissionToPo: 无操作权限信息！");

        // 操作权限的转换
        for (OpsEnum opsEnum : OpsEnum.values())
            if (permissions.contains(opsEnum.getCode())) ops.add(opsEnum.getName());

        // 得到 PermissionPo
        PermissionPo permissionPo = new PermissionPo();
        permissionPo.setBusiness(permission.getBusiness());
        permissionPo.setRoleId(permission.getRoleId());
        permissionPo.setId(permissionPo.getId());
        permissionPo.setOps(ops);
        return permissionPo;
    }

    private Permission poToPermission(PermissionPo permissionPo){
        List<String> ops = permissionPo.getOps();
        if (ops==null || ops.isEmpty()) throw new DBException("poToPermission: 无操作权限信息！");
        StringBuilder sb = new StringBuilder();

        // 操作权限转换
        for (String op : ops) {
            for (OpsEnum opsEnum : OpsEnum.values()) {
                opsEnum.getName().equals(op);
                sb.append(opsEnum.getCode());
            }
        }
        // 返回结果
        Permission permission = new Permission();
        permission.setRoleId(permissionPo.getRoleId());
        permission.setBusiness(permissionPo.getBusiness());
        permission.setPermissions(sb.toString());
        return permission;
    }

}
