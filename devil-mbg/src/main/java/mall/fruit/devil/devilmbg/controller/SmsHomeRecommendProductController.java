package mall.fruit.devil.devilmbg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.fruit.devil.devilcommon.api.CommonPage;
import mall.fruit.devil.devilcommon.api.CommonResult;
import mall.fruit.devil.devilmbg.entity.SmsHomeRecommendProduct;
import mall.fruit.devil.devilmbg.mapper.SmsHomeRecommendProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 人气推荐商品表 前端控制器
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@RestController
@Api(tags = "SmsHomeRecommendProductController", description = "首页人气推荐管理")
@RequestMapping("/home/recommendProduct")
public class SmsHomeRecommendProductController {
    @Autowired
    private SmsHomeRecommendProductMapper recommendProductMapper;

    @ApiOperation("添加首页推荐")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create( List<SmsHomeRecommendProduct> homeRecommendProductList) {
        for (SmsHomeRecommendProduct recommendProduct : homeRecommendProductList) {
            recommendProduct.setRecommendStatus(1);
            recommendProduct.setSort(0);
            recommendProductMapper.insert(recommendProduct);
        }
        int count = homeRecommendProductList.size();
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation("修改推荐排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    public CommonResult updateSort(@PathVariable Long id, Integer sort) {
        SmsHomeRecommendProduct recommendProduct = new SmsHomeRecommendProduct();
        recommendProduct.setId(id);
        recommendProduct.setSort(sort);
        int count = recommendProductMapper.updateById(recommendProduct);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量删除推荐")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        QueryWrapper<SmsHomeRecommendProduct> wrapper = new QueryWrapper<>();
        wrapper.in("id",ids);
        int count = recommendProductMapper.delete(wrapper);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量修改推荐状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        QueryWrapper<SmsHomeRecommendProduct> wrapper = new QueryWrapper<>();
        wrapper.in("id",ids);
        SmsHomeRecommendProduct record = new SmsHomeRecommendProduct();
        record.setRecommendStatus(recommendStatus);
        int count =  recommendProductMapper.update(record,wrapper);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("分页查询推荐")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<SmsHomeRecommendProduct>> list(@RequestParam(value = "productName", required = false) String productName,
                                                                  @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<SmsHomeRecommendProduct> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SmsHomeRecommendProduct> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(productName)){
            wrapper.like("product_name",productName);
        }
        if(recommendStatus!=null){
            wrapper.eq("recommend_status",recommendStatus);
        }
        wrapper.orderByDesc("sort");
        Page<SmsHomeRecommendProduct> result = recommendProductMapper.selectPage(page,wrapper);
        return CommonResult.success(CommonPage.restPage(result));
    }
}
