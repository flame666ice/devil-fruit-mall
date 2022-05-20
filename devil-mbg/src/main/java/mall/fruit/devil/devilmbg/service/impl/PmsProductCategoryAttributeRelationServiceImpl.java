package mall.fruit.devil.devilmbg.service.impl;

import mall.fruit.devil.devilmbg.entity.PmsProductCategoryAttributeRelation;
import mall.fruit.devil.devilmbg.mapper.PmsProductCategoryAttributeRelationMapper;
import mall.fruit.devil.devilmbg.service.IPmsProductCategoryAttributeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@Service
public class PmsProductCategoryAttributeRelationServiceImpl extends ServiceImpl<PmsProductCategoryAttributeRelationMapper, PmsProductCategoryAttributeRelation> implements IPmsProductCategoryAttributeRelationService {

}
