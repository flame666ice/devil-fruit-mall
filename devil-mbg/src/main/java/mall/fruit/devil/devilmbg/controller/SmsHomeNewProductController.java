package mall.fruit.devil.devilmbg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.deploy.net.proxy.RemoveCommentReader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.fruit.devil.devilcommon.api.CommonPage;
import mall.fruit.devil.devilcommon.api.CommonResult;
import mall.fruit.devil.devilmbg.entity.SmsHomeNewProduct;
import mall.fruit.devil.devilmbg.mapper.SmsHomeNewProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 新鲜好物表 前端控制器
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@RestController
@Api(tags = "SmsHomeNewProductController", description = "首页新品管理")
@RequestMapping("/home/newProduct")
public class SmsHomeNewProductController {

    @Autowired
    private SmsHomeNewProductMapper homeNewProductMapper;

    @ApiOperation("添加首页新品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create( List<SmsHomeNewProduct> homeNewProductList) {
        for (SmsHomeNewProduct SmsHomeNewProduct : homeNewProductList) {
            SmsHomeNewProduct.setRecommendStatus(1);
            SmsHomeNewProduct.setSort(0);
            homeNewProductMapper.insert(SmsHomeNewProduct);
        }
        int count =  homeNewProductList.size();
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改首页新品排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    public CommonResult updateSort(@PathVariable Long id, Integer sort) {
        SmsHomeNewProduct homeNewProduct = new SmsHomeNewProduct();
        homeNewProduct.setId(id);
        homeNewProduct.setSort(sort);
        int count = homeNewProductMapper.updateById(homeNewProduct);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量删除首页新品")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        QueryWrapper<SmsHomeNewProduct> wrapper = new QueryWrapper<>();
        wrapper.in("id",ids);
        int count = homeNewProductMapper.delete(wrapper);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量修改首页新品状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        UpdateWrapper<SmsHomeNewProduct> wrapper = new UpdateWrapper<>();
        wrapper.in("id",ids);
        SmsHomeNewProduct record = new SmsHomeNewProduct();
        record.setRecommendStatus(recommendStatus);
        int count = homeNewProductMapper.update(record,wrapper);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("分页查询首页新品")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<SmsHomeNewProduct>> list(@RequestParam(value = "productName", required = false) String productName,
                                                            @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<SmsHomeNewProduct> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SmsHomeNewProduct> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(productName)){
            wrapper.like("product_name",productName);
        }
        if(recommendStatus!=null){
            wrapper.eq("recommend_status", recommendStatus);
        }
        wrapper.orderByDesc("sort");
        Page<SmsHomeNewProduct> result = homeNewProductMapper.selectPage(page,wrapper);
        return CommonResult.success(CommonPage.restPage(result));
    }

}
