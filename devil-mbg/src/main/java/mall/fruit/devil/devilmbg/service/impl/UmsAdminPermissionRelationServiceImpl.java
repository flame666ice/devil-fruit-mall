package mall.fruit.devil.devilmbg.service.impl;

import mall.fruit.devil.devilmbg.entity.UmsAdminPermissionRelation;
import mall.fruit.devil.devilmbg.mapper.UmsAdminPermissionRelationMapper;
import mall.fruit.devil.devilmbg.service.IUmsAdminPermissionRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限) 服务实现类
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@Service
public class UmsAdminPermissionRelationServiceImpl extends ServiceImpl<UmsAdminPermissionRelationMapper, UmsAdminPermissionRelation> implements IUmsAdminPermissionRelationService {

}
