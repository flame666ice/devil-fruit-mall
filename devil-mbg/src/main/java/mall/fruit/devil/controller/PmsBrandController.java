package mall.fruit.devil.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.fruit.devil.devilcommon.api.CommonPage;
import mall.fruit.devil.devilcommon.api.CommonResult;
import mall.fruit.devil.entity.PmsBrand;
import mall.fruit.devil.entity.PmsProduct;
import mall.fruit.devil.entitycustom.PmsBrandParam;
import mall.fruit.devil.mapper.PmsBrandMapper;
import mall.fruit.devil.mapper.PmsProductMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@RestController
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    @Autowired
    private PmsProductMapper pmsProductMapper;

    @ApiOperation(value = "获取全部品牌列表")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult<List<PmsBrand>> getList() {
        return CommonResult.success(pmsBrandMapper.selectList(Wrappers.emptyWrapper()));
    }


    @ApiOperation(value = "添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@Validated @RequestBody PmsBrandParam pmsBrand) {
        CommonResult commonResult;

        PmsBrand mBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrand, mBrand);
        mBrand.setId(null);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(mBrand.getFirstLetter())) {
            mBrand.setFirstLetter(mBrand.getName().substring(0, 1));
        }
        int count = pmsBrandMapper.insert(mBrand);
        Long id = mBrand.getId();
        Long test = id;
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation(value = "更新品牌")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable("id") Long id,
                               @Validated @RequestBody PmsBrandParam pmsBrandParam) {
        CommonResult commonResult;
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        pmsBrand.setId(id);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        //更新品牌时要更新商品中的品牌名称
        PmsProduct product = new PmsProduct();
        product.setBrandName(pmsBrand.getName());
        UpdateWrapper<PmsProduct> wrapper = new UpdateWrapper<PmsProduct>();
        wrapper.eq("brandId",id);
        pmsProductMapper.update(product,wrapper);

        int count = pmsBrandMapper.updateById(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @ApiOperation(value = "删除品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = pmsBrandMapper.deleteById(id);
        if (count == 1) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "根据品牌名称分页获取品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsBrand>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<PmsBrand> page = new Page<>(pageNum,pageSize);
        QueryWrapper<PmsBrand> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("sort");
        if (!StringUtils.isEmpty(keyword)) {
            wrapper.like("name",keyword);
        }
        Page<PmsBrand> result =  pmsBrandMapper.selectPage(page,wrapper);
        return CommonResult.success(CommonPage.restPage(result));
    }

    @ApiOperation(value = "根据编号查询品牌信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<PmsBrand> getItem(@PathVariable("id") Long id) {
        return CommonResult.success(pmsBrandMapper.selectById(id));
    }

    @ApiOperation(value = "批量删除品牌")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    public CommonResult deleteBatch(@RequestParam("ids") List<Long> ids) {
        QueryWrapper<PmsBrand> wrapper = new QueryWrapper<>();
        wrapper.in("id",ids);
        int count = pmsBrandMapper.delete(wrapper);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "批量更新显示状态")
    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids,
                                         @RequestParam("showStatus") Integer showStatus) {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setShowStatus(showStatus);
        UpdateWrapper<PmsBrand> wrapper = new UpdateWrapper<>();
        wrapper.in("id",ids);
        int count = pmsBrandMapper.update(pmsBrand, wrapper);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation(value = "批量更新厂家制造商状态")
    @RequestMapping(value = "/update/factoryStatus", method = RequestMethod.POST)
    public CommonResult updateFactoryStatus(@RequestParam("ids") List<Long> ids,
                                            @RequestParam("factoryStatus") Integer factoryStatus) {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setFactoryStatus(factoryStatus);
        UpdateWrapper<PmsBrand> wrapper = new UpdateWrapper<>();
        wrapper.in("id",ids);
        int count = pmsBrandMapper.update(pmsBrand, wrapper);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
