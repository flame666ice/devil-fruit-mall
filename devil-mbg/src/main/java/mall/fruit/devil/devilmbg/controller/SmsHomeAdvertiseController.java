package mall.fruit.devil.devilmbg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.fruit.devil.devilcommon.api.CommonPage;
import mall.fruit.devil.devilcommon.api.CommonResult;
import mall.fruit.devil.devilmbg.entity.SmsHomeAdvertise;
import mall.fruit.devil.devilmbg.mapper.SmsHomeAdvertiseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 首页轮播广告表 前端控制器
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@RestController
@Api(tags = "SmsHomeAdvertiseController", description = "首页轮播广告管理")
@RequestMapping("/home/advertise")
public class SmsHomeAdvertiseController {

    @Autowired
    private SmsHomeAdvertiseMapper advertiseMapper;

    @ApiOperation("添加广告")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(SmsHomeAdvertise advertise) {
        advertise.setClickCount(0);
        advertise.setOrderCount(0);
        int count = advertiseMapper.insert(advertise);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("删除广告")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        QueryWrapper<SmsHomeAdvertise> wrapper = new QueryWrapper<>();
        wrapper.in("id",ids);
        int count = advertiseMapper.delete(wrapper);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }


    @ApiOperation("修改上下线状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    public CommonResult updateStatus(@PathVariable Long id, Integer status) {
        SmsHomeAdvertise record = new SmsHomeAdvertise();
        record.setId(id);
        record.setStatus(status);
        int count = advertiseMapper.updateById(record);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("获取广告详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<SmsHomeAdvertise> getItem(@PathVariable Long id) {
        SmsHomeAdvertise advertise = advertiseMapper.selectById(id);
        return CommonResult.success(advertise);
    }

    @ApiOperation("修改广告")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id,  SmsHomeAdvertise advertise) {
        advertise.setId(id);
        int count =  advertiseMapper.updateById(advertise);
        if (count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation("分页查询广告")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<SmsHomeAdvertise>> list(@RequestParam(value = "name", required = false) String name,
                                                           @RequestParam(value = "type", required = false) Integer type,
                                                           @RequestParam(value = "endTime", required = false) String endTime,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<SmsHomeAdvertise> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SmsHomeAdvertise> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name",name);
        }
        if (type != null) {
            wrapper.eq("type",type);
        }
        if (!StringUtils.hasText(endTime)) {
            String startStr = endTime + " 00:00:00";
            String endStr = endTime + " 23:59:59";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = null;
            try {
                start = sdf.parse(startStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date end = null;
            try {
                end = sdf.parse(endStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (start != null && end != null) {
                wrapper.between("end_time", start,end);
            }
        }
        wrapper.orderByDesc("sort");
        Page<SmsHomeAdvertise> result = advertiseMapper.selectPage(page,wrapper);
        return CommonResult.success(CommonPage.restPage(result));
    }

}
