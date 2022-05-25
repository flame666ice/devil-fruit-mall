package mall.fruit.devil.devilmbg.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.fruit.devil.devilcommon.api.CommonPage;
import mall.fruit.devil.devilcommon.api.CommonResult;
import mall.fruit.devil.devilmbg.entity.OmsOrder;
import mall.fruit.devil.devilmbg.service.impl.OmsOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.PrinterAbortException;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@RestController
@Api(tags = "OmsOrderController", description = "订单管理")
@RequestMapping("/order")
public class OmsOrderController {

    @Autowired
    private OmsOrderServiceImpl omsOrderService;


    @ApiOperation("查询订单")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<OmsOrder>> list(OmsOrderQuery){

    }

}
