package mall.fruit.devil.controller;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import mall.fruit.devil.devilcommon.api.CommonResult;
import mall.fruit.devil.entity.CmsPreferenceArea;
import mall.fruit.devil.service.ICmsPreferenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 优选专区 前端控制器
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@RestController
@Api(tags = "CmsPrefrenceAreaController", description = "商品优选管理")
@RequestMapping("/prefrenceArea")
public class CmsPreferenceAreaController {

    @Autowired
    private ICmsPreferenceAreaService iCmsPreferenceAreaService;

    @ApiOperation("获取所有商品优选")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult<List<CmsPreferenceArea>> listAll(){
        List<CmsPreferenceArea> preferenceAreaList = iCmsPreferenceAreaService.list();
        return CommonResult.success(preferenceAreaList);
    }

}
