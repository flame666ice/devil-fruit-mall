package mall.fruit.devil.devilmbg.controller;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.fruit.devil.devilcommon.api.CommonPage;
import mall.fruit.devil.devilcommon.api.CommonResult;
import mall.fruit.devil.devilmbg.entity.OmsOrderReturnReason;
import mall.fruit.devil.devilmbg.entitycustom.UpdateAdminPasswordParam;
import mall.fruit.devil.devilmbg.mapper.OmsOrderReturnReasonMapper;
import mall.fruit.devil.devilmbg.service.IOmsOrderReturnReasonService;
import mall.fruit.devil.devilmbg.service.impl.OmsOrderReturnReasonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 退货原因表 前端控制器
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@RestController
@Api(tags = "OmsOrderReturnReasonController", description = "退货原因管理")
@RequestMapping("/returnReason")
public class OmsOrderReturnReasonController {

    @Autowired
    private IOmsOrderReturnReasonService iOmsOrderReturnReasonService;

    @Autowired
    private OmsOrderReturnReasonMapper omsOrderReturnReasonMapper;

    @ApiOperation("添加退货原因")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(OmsOrderReturnReason returnReason) {
        returnReason.setCreateTime(LocalDateTime.now());
        int count = omsOrderReturnReasonMapper.insert(returnReason);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改退货原因")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, OmsOrderReturnReason returnReason) {
        returnReason.setId(id);
        int count = omsOrderReturnReasonMapper.updateById(returnReason);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量删除退货原因")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {

        QueryWrapper<OmsOrderReturnReason> omsOrderReturnReasonQueryWrapper = new QueryWrapper<OmsOrderReturnReason>();
        omsOrderReturnReasonQueryWrapper.in("id",ids);
        int count = omsOrderReturnReasonMapper.delete(omsOrderReturnReasonQueryWrapper);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("分页查询退货原因")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<OmsOrderReturnReason>> list(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<OmsOrderReturnReason> queryPage = new Page<OmsOrderReturnReason>(pageNum,pageSize);

        Page<OmsOrderReturnReason> resultPage= omsOrderReturnReasonMapper.selectPage(queryPage, Wrappers.emptyWrapper());
        return CommonResult.success(CommonPage.restPage(resultPage));
    }

    @ApiOperation("获取单个退货原因详情信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<OmsOrderReturnReason> getItem(@PathVariable Long id) {
        OmsOrderReturnReason reason = omsOrderReturnReasonMapper.selectById(id);
        return CommonResult.success(reason);
    }


    @ApiOperation("修改退货原因启用状态")
    @RequestMapping(value = "/update/status", method = RequestMethod.POST)
    public CommonResult updateStatus(@RequestParam(value = "status") Integer status,
                                     @RequestParam("ids") List<Long> ids) {
        int count ;
        if(!status.equals(0) && !status.equals(1))
            count = 0;
        OmsOrderReturnReason record = new OmsOrderReturnReason();
        record.setStatus(status);

        UpdateWrapper<OmsOrderReturnReason> updateWrapper = new UpdateWrapper<OmsOrderReturnReason>();
        updateWrapper.in("id",ids);
        count = omsOrderReturnReasonMapper.update(record,updateWrapper);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

}
