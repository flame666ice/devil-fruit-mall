package mall.fruit.devil.devilmbg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.fruit.devil.devilcommon.api.CommonPage;
import mall.fruit.devil.devilcommon.api.CommonResult;
import mall.fruit.devil.devilmbg.entity.CmsSubject;
import mall.fruit.devil.devilmbg.entitycustom.OmsOrderDeliveryParam;
import mall.fruit.devil.devilmbg.service.ICmsSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 专题表 前端控制器
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@RestController
@Api(tags = "CmsSubjectController", description = "商品专题管理")
@RequestMapping("/subject")
public class CmsSubjectController {

    @Autowired
    private ICmsSubjectService iCmsSubjectService;

    @ApiOperation("获取全部商品专题")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult<List<CmsSubject>> listAll(){
        List<CmsSubject> cmsSubjectList = iCmsSubjectService.list();
        return CommonResult.success(cmsSubjectList);
    }

    @ApiOperation(value = "根据专题名称分页获取商品专题")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<CmsSubject>> getList(@RequestParam(value = "keyword", required = false) String keyword ,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum ,
                                                        @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize){

        Page productPage = new Page(pageNum,pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("title",keyword);
        Page<CmsSubject> pageInfo = iCmsSubjectService.page(productPage,queryWrapper);
        return CommonResult.success(CommonPage.restPage(pageInfo));
    }

}
