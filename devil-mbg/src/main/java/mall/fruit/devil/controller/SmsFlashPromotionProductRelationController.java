package mall.fruit.devil.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.fruit.devil.devilcommon.api.CommonPage;
import mall.fruit.devil.devilcommon.api.CommonResult;
import mall.fruit.devil.entity.SmsFlashPromotionProductRelation;
import mall.fruit.devil.entitycustom.SmsFlashPromotionProduct;
import mall.fruit.devil.mapper.SmsFlashPromotionProductRelationMapper;
import mall.fruit.devil.mappercustom.SmsFlashPromotionProductRelationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 前端控制器
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@RestController
@Api(tags = "SmsFlashPromotionProductRelationController", description = "限时购和商品关系管理")
@RequestMapping("/flashProductRelation")
public class SmsFlashPromotionProductRelationController {

    @Autowired
    private SmsFlashPromotionProductRelationMapper relationMapper;
    @Autowired
    private SmsFlashPromotionProductRelationDao relationDao;

    @ApiOperation("批量选择商品添加关联")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody List<SmsFlashPromotionProductRelation> relationList) {
        for (SmsFlashPromotionProductRelation relation : relationList) {
            relationMapper.insert(relation);
        }
        int count = relationList.size();
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改关联信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody SmsFlashPromotionProductRelation relation) {
        relation.setId(id);
        int count = relationMapper.updateById(relation);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除关联")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id) {
        int count = relationMapper.deleteById(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取管理商品促销信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<SmsFlashPromotionProductRelation> getItem(@PathVariable Long id) {
        SmsFlashPromotionProductRelation relation = relationMapper.selectById(id);
        return CommonResult.success(relation);
    }

    @ApiOperation("分页查询不同场次关联及商品信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<SmsFlashPromotionProduct>> list(@RequestParam(value = "flashPromotionId") Long flashPromotionId,
                                                                   @RequestParam(value = "flashPromotionSessionId") Long flashPromotionSessionId,
                                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<SmsFlashPromotionProduct> page = new Page<>(pageNum,pageSize);
        Page<SmsFlashPromotionProduct> result = (Page<SmsFlashPromotionProduct>) relationDao.getList(page,flashPromotionId,flashPromotionSessionId);
        return CommonResult.success(CommonPage.restPage(result));
    }
}
