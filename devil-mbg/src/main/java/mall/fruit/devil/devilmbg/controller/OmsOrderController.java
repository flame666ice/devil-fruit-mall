package mall.fruit.devil.devilmbg.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.fruit.devil.devilcommon.api.CommonPage;
import mall.fruit.devil.devilcommon.api.CommonResult;
import mall.fruit.devil.devilmbg.entitycustom.OmsOrderDetail;
import mall.fruit.devil.devilmbg.entitycustom.OmsOrderQueryParam;
import mall.fruit.devil.devilmbg.entity.OmsOrder;
import mall.fruit.devil.devilmbg.mappercustom.OmsOrderDao;
import mall.fruit.devil.devilmbg.service.impl.OmsOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private OmsOrderDao omsOrderDao;


    @ApiOperation("查询订单")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<OmsOrder>> list(OmsOrderQueryParam omsOrderQueryParam,
                                                   @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){

        Page orderDetailPage = new Page<OmsOrder>(pageNum,pageSize);
        Page<OmsOrder> omsOrderPage = (Page<OmsOrder>) omsOrderDao.getList(orderDetailPage,omsOrderQueryParam);
        return CommonResult.success(CommonPage.restPage(omsOrderPage));
    }

}
