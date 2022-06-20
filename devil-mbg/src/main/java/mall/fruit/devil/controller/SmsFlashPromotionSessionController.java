package mall.fruit.devil.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.fruit.devil.devilcommon.api.CommonResult;
import mall.fruit.devil.entity.SmsFlashPromotionProductRelation;
import mall.fruit.devil.entity.SmsFlashPromotionSession;
import mall.fruit.devil.entitycustom.SmsFlashPromotionSessionDetail;
import mall.fruit.devil.mapper.SmsFlashPromotionProductRelationMapper;
import mall.fruit.devil.mapper.SmsFlashPromotionSessionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 限时购场次表 前端控制器
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@RestController
@Api(tags = "SmsFlashPromotionSessionController", description = "限时购场次管理")
@RequestMapping("/flashSession")
public class SmsFlashPromotionSessionController {


    @Autowired
    private SmsFlashPromotionSessionMapper promotionSessionMapper;

    @Autowired
    private SmsFlashPromotionProductRelationMapper relationMapper;

    @ApiOperation("添加场次")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody SmsFlashPromotionSession promotionSession) {
        promotionSession.setCreateTime(LocalDateTime.now());
        int count = promotionSessionMapper.insert(promotionSession);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改场次")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody SmsFlashPromotionSession promotionSession) {
        promotionSession.setId(id);
        int count = promotionSessionMapper.updateById(promotionSession);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改启用状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    public CommonResult updateStatus(@PathVariable Long id, Integer status) {
        SmsFlashPromotionSession promotionSession = new SmsFlashPromotionSession();
        promotionSession.setId(id);
        promotionSession.setStatus(status);
        int count = promotionSessionMapper.updateById(promotionSession);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除场次")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id) {
        int count = promotionSessionMapper.deleteById(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取场次详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<SmsFlashPromotionSession> getItem(@PathVariable Long id) {
        SmsFlashPromotionSession promotionSession = promotionSessionMapper.selectById(id);
        return CommonResult.success(promotionSession);
    }

    @ApiOperation("获取全部场次")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<SmsFlashPromotionSession>> list() {
        List<SmsFlashPromotionSession> promotionSessionList = promotionSessionMapper.selectList(Wrappers.emptyWrapper());
        return CommonResult.success(promotionSessionList);
    }

    @ApiOperation("获取全部可选场次及其数量")
    @RequestMapping(value = "/selectList", method = RequestMethod.GET)
    public CommonResult<List<SmsFlashPromotionSessionDetail>> selectList(Long flashPromotionId) {
        List<SmsFlashPromotionSessionDetail> result = new ArrayList<>();
        QueryWrapper<SmsFlashPromotionSession> wrapper = new QueryWrapper<>();
        wrapper.eq("status",1);
        List<SmsFlashPromotionSession> list = promotionSessionMapper.selectList(wrapper);
        for (SmsFlashPromotionSession promotionSession : list) {
            SmsFlashPromotionSessionDetail detail = new SmsFlashPromotionSessionDetail();
            BeanUtils.copyProperties(promotionSession, detail);
            QueryWrapper<SmsFlashPromotionProductRelation> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("flash_promotion_id",flashPromotionId).eq("flash_promotion_session_id",promotionSession.getId());
            long count = relationMapper.selectCount(wrapper1);
            detail.setProductCount(count);
            result.add(detail);
        }
        return CommonResult.success(result);
    }
}
