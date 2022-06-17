package mall.fruit.devil.devilmbg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import mall.fruit.devil.devilmbg.entity.*;
import mall.fruit.devil.devilmbg.mapper.UmsRoleMapper;
import mall.fruit.devil.devilmbg.mapper.UmsRoleMenuRelationMapper;
import mall.fruit.devil.devilmbg.mapper.UmsRoleResourceRelationMapper;
import mall.fruit.devil.devilmbg.mappercustom.UmsRoleDao;
import mall.fruit.devil.devilmbg.service.IUmsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mall.fruit.devil.devilmbg.service.UmsAdminCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {

    @Autowired
    private UmsRoleMapper roleMapper;
    @Autowired
    private UmsRoleMenuRelationMapper roleMenuRelationMapper;
    @Autowired
    private UmsRoleResourceRelationMapper roleResourceRelationMapper;
    @Autowired
    private UmsRoleDao roleDao;
    @Autowired
    private UmsAdminCacheService adminCacheService;
    @Override
    public int create(UmsRole role) {
        role.setCreateTime(LocalDateTime.now());
        role.setAdminCount(0);
        role.setSort(0);
        return roleMapper.insert(role);
    }

    @Override
    public int update(Long id, UmsRole role) {
        role.setId(id);
        return roleMapper.updateById(role);
    }

    @Override
    public int delete(List<Long> ids) {
        QueryWrapper<UmsRole> wrapper = new QueryWrapper<>();
        wrapper.in("id",ids);
        int count = roleMapper.delete(wrapper);
        adminCacheService.delResourceListByRoleIds(ids);
        return count;
    }
    @Override
    public List<UmsRole> list(){
        return roleMapper.selectList(Wrappers.emptyWrapper());
    };
    @Override
    public List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        Page<UmsRole> page = new Page<>(pageNum,pageSize);
        QueryWrapper<UmsRole> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)) {
            wrapper.like("name",keyword);
        }
        return roleMapper.selectList(wrapper);
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return roleDao.getMenuList(adminId);
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return roleDao.getMenuListByRoleId(roleId);
    }

    @Override
    public List<UmsResource> listResource(Long roleId) {
        return roleDao.getResourceListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        //先删除原有关系
        QueryWrapper<UmsRoleMenuRelation> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId);
        roleMenuRelationMapper.delete(wrapper);
        //批量插入新关系
        for (Long menuId : menuIds) {
            UmsRoleMenuRelation relation = new UmsRoleMenuRelation();
            relation.setRoleId(roleId);
            relation.setMenuId(menuId);
            roleMenuRelationMapper.insert(relation);
        }
        return menuIds.size();
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        //先删除原有关系
        QueryWrapper<UmsRoleResourceRelation> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId);
        roleResourceRelationMapper.delete(wrapper);
        //批量插入新关系
        for (Long resourceId : resourceIds) {
            UmsRoleResourceRelation relation = new UmsRoleResourceRelation();
            relation.setRoleId(roleId);
            relation.setResourceId(resourceId);
            roleResourceRelationMapper.insert(relation);
        }
        adminCacheService.delResourceListByRole(roleId);
        return resourceIds.size();
    }
}
