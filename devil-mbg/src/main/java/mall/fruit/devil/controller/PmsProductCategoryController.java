package mall.fruit.devil.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.fruit.devil.devilcommon.api.CommonPage;
import mall.fruit.devil.devilcommon.api.CommonResult;
import mall.fruit.devil.entity.PmsProduct;
import mall.fruit.devil.entity.PmsProductCategory;
import mall.fruit.devil.entity.PmsProductCategoryAttributeRelation;
import mall.fruit.devil.entitycustom.PmsProductCategoryParam;
import mall.fruit.devil.entitycustom.PmsProductCategoryWithChildrenItem;
import mall.fruit.devil.mapper.PmsProductCategoryAttributeRelationMapper;
import mall.fruit.devil.mapper.PmsProductCategoryMapper;
import mall.fruit.devil.mapper.PmsProductMapper;
import mall.fruit.devil.mappercustom.PmsProductCategoryAttributeRelationDao;
import mall.fruit.devil.mappercustom.PmsProductCategoryDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@RestController
@Api(tags = "PmsProductCategoryController", description = "商品分类管理")
@RequestMapping("/productCategory")
public class PmsProductCategoryController {


    @Autowired
    private PmsProductCategoryMapper pmsProductCategoryMapper;

    @Autowired
    private PmsProductCategoryAttributeRelationDao pmsProductCategoryAttributeRelationDao;

    @Autowired
    private PmsProductCategoryAttributeRelationMapper pmsProductCategoryAttributeRelationMapper;

    @Autowired
    private PmsProductMapper pmsProductMapper;

    @Autowired
    private PmsProductCategoryDao pmsProductCategoryDao;

    @ApiOperation("添加商品分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@Validated @RequestBody PmsProductCategoryParam productCategoryParam) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setProductCount(0);
        BeanUtils.copyProperties(productCategoryParam, productCategory);
        //没有父分类时为一级分类
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            //有父分类时选择根据父分类level设置
            PmsProductCategory parentCategory = pmsProductCategoryMapper.selectById(productCategory.getParentId());
            if (parentCategory != null) {
                productCategory.setLevel(parentCategory.getLevel() + 1);
            } else {
                productCategory.setLevel(0);
            }
        }
        int count = pmsProductCategoryMapper.insert(productCategory);
        //创建筛选属性关联
        List<Long> productAttributeIdList = productCategoryParam.getProductAttributeIdList();
        if(!CollectionUtils.isEmpty(productAttributeIdList)){
            List<PmsProductCategoryAttributeRelation> relationList = new ArrayList<>();
            for (Long productAttrId : productAttributeIdList) {
                PmsProductCategoryAttributeRelation relation = new PmsProductCategoryAttributeRelation();
                relation.setProductAttributeId(productAttrId);
                relation.setProductCategoryId(productCategory.getId());
                relationList.add(relation);
            }
            pmsProductCategoryAttributeRelationDao.insertList(relationList);
        }

        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("修改商品分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id,
                               @Validated @RequestBody PmsProductCategoryParam productCategoryParam) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setId(id);
        BeanUtils.copyProperties(productCategoryParam, productCategory);
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            //有父分类时选择根据父分类level设置
            PmsProductCategory parentCategory = pmsProductCategoryMapper.selectById(productCategory.getParentId());
            if (parentCategory != null) {
                productCategory.setLevel(parentCategory.getLevel() + 1);
            } else {
                productCategory.setLevel(0);
            }
        }
        //更新商品分类时要更新商品中的名称
        PmsProduct product = new PmsProduct();
        product.setProductCategoryName(productCategory.getName());
        UpdateWrapper<PmsProduct> wrapper = new UpdateWrapper<PmsProduct>();
        wrapper.eq("product_category_id",id);
        pmsProductMapper.update(product,wrapper);
        //同时更新筛选属性的信息
        if(!CollectionUtils.isEmpty(productCategoryParam.getProductAttributeIdList())){
            QueryWrapper<PmsProductCategoryAttributeRelation> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("product_category_id",id);
            pmsProductCategoryAttributeRelationMapper.delete(wrapper1);
            List<PmsProductCategoryAttributeRelation> relationList = new ArrayList<>();
            for (Long productAttrId : productCategoryParam.getProductAttributeIdList()) {
                PmsProductCategoryAttributeRelation relation = new PmsProductCategoryAttributeRelation();
                relation.setProductAttributeId(productAttrId);
                relation.setProductCategoryId(id);
                relationList.add(relation);
            }
            pmsProductCategoryAttributeRelationDao.insertList(relationList);
        }else{
            QueryWrapper<PmsProductCategoryAttributeRelation> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("product_category_id",id);
            pmsProductCategoryAttributeRelationMapper.delete(wrapper1);
        }
        int count =  pmsProductCategoryMapper.updateById(productCategory);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("分页查询商品分类")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsProductCategory>> getList(@PathVariable Long parentId,
                                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<PmsProductCategory> queryPage = new Page<>(pageNum,pageSize);
        QueryWrapper<PmsProductCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",parentId).orderByDesc("sort");
        Page<PmsProductCategory> result = pmsProductCategoryMapper.selectPage(queryPage,wrapper);
        return CommonResult.success(CommonPage.restPage(result));
    }


    @ApiOperation("根据id获取商品分类")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<PmsProductCategory> getItem(@PathVariable Long id) {
        PmsProductCategory productCategory = pmsProductCategoryMapper.selectById(id);
        return CommonResult.success(productCategory);
    }


    @ApiOperation("删除商品分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id) {
        int count = pmsProductCategoryMapper.deleteById(id);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("修改导航栏显示状态")
    @RequestMapping(value = "/update/navStatus", method = RequestMethod.POST)
    public CommonResult updateNavStatus(@RequestParam("ids") List<Long> ids, @RequestParam("navStatus") Integer navStatus) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setNavStatus(navStatus);
        UpdateWrapper<PmsProductCategory> wrapper = new UpdateWrapper<>();
        wrapper.in("id",ids);
        int count = pmsProductCategoryMapper.update(productCategory, wrapper);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改显示状态")
    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
        PmsProductCategory productCategory = new PmsProductCategory();
        productCategory.setShowStatus(showStatus);
        UpdateWrapper<PmsProductCategory> wrapper = new UpdateWrapper<>();
        wrapper.in("id",ids);
        int count = pmsProductCategoryMapper.update(productCategory, wrapper);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("查询所有一级分类及子分类")
    @RequestMapping(value = "/list/withChildren", method = RequestMethod.GET)
    public CommonResult<List<PmsProductCategoryWithChildrenItem>> listWithChildren() {
        List<PmsProductCategoryWithChildrenItem> list  =  pmsProductCategoryDao.listWithChildren();
        return CommonResult.success(list);
    }
}
