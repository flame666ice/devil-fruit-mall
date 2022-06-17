package mall.fruit.devil.devilmbg.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import mall.fruit.devil.devilmbg.entity.UmsResource;
import mall.fruit.devil.devilmbg.mapper.UmsResourceMapper;
import mall.fruit.devil.devilmbg.service.IUmsResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mall.fruit.devil.devilmbg.service.UmsAdminCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台资源表 服务实现类
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@Service
public class UmsResourceServiceImpl extends ServiceImpl<UmsResourceMapper, UmsResource> implements IUmsResourceService {

    @Autowired
    private UmsResourceMapper resourceMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;

    @Override
    public int create(UmsResource umsResource) {
        umsResource.setCreateTime(LocalDateTime.now());
        return resourceMapper.insert(umsResource);
    }

    @Override
    public int update(Long id, UmsResource umsResource) {
        umsResource.setId(id);
        int count = resourceMapper.updateById(umsResource);
        adminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public UmsResource getItem(Long id) {
        return resourceMapper.selectById(id);
    }

    @Override
    public int delete(Long id) {
        int count = resourceMapper.deleteById(id);
        adminCacheService.delResourceListByResource(id);
        return count;
    }

    @Override
    public List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        Page<UmsResource> page = new Page<>(pageNum,pageSize);
        QueryWrapper<UmsResource> wrapper = new QueryWrapper<>();
        if(categoryId!=null){
            wrapper.eq("category_id",categoryId);
        }
        if(StrUtil.isNotEmpty(nameKeyword)){
            wrapper.like("name",nameKeyword);
        }
        if(StrUtil.isNotEmpty(urlKeyword)){
            wrapper.like("url",urlKeyword);
        }
        Page<UmsResource> result = resourceMapper.selectPage(page,wrapper);
        return result.getRecords();
    }

    @Override
    public List<UmsResource> listAll() {
        return resourceMapper.selectList(Wrappers.emptyWrapper());
    }
}
