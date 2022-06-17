package mall.fruit.devil.devilmbg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.fruit.devil.devilcommon.api.CommonResult;
import mall.fruit.devil.devilmbg.entity.UmsMemberLevel;
import mall.fruit.devil.devilmbg.service.IUmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 会员等级表 前端控制器
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@RestController
@Api(tags = "UmsMemberLevelController", description = "会员等级管理")
@RequestMapping("/memberLevel")
public class UmsMemberLevelController {
    @Autowired
    private IUmsMemberLevelService memberLevelService;

    @ApiOperation("查询所有会员等级")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<UmsMemberLevel>> list(@RequestParam("defaultStatus") Integer defaultStatus) {
        QueryWrapper<UmsMemberLevel> wrapper = new QueryWrapper<>();
        wrapper.eq("default_status",defaultStatus);
        List<UmsMemberLevel> memberLevelList = memberLevelService.list(wrapper);
        return CommonResult.success(memberLevelList);
    }

}
