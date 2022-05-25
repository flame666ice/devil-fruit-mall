package mall.fruit.devil.devilmbg.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.fruit.devil.devilcommon.api.CommonResult;
import mall.fruit.devil.devilmbg.entity.OmsCompanyAddress;
import mall.fruit.devil.devilmbg.service.impl.OmsCompanyAddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 公司收发货地址表 前端控制器
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@RestController
@Api(tags = "OmsCompanyAddressController", description = "收货地址管理")
@RequestMapping("/companyAddress")
public class OmsCompanyAddressController {

    @Autowired
    private OmsCompanyAddressServiceImpl omsCompanyAddressService;

    @ApiOperation("获取所有收货地址")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<OmsCompanyAddress>> list(){
        List<OmsCompanyAddress> omsCompanyAddressList = omsCompanyAddressService.list();
        return CommonResult.success(omsCompanyAddressList);
    }

}
