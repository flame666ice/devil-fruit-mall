package mall.fruit.devil.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mall.fruit.devil.devilcommon.api.CommonPage;
import mall.fruit.devil.devilcommon.api.CommonResult;
import mall.fruit.devil.entity.OmsOrderOperateHistory;
import mall.fruit.devil.entitycustom.*;
import mall.fruit.devil.entity.OmsOrder;
import mall.fruit.devil.mapper.OmsOrderMapper;
import mall.fruit.devil.mapper.OmsOrderOperateHistoryMapper;
import mall.fruit.devil.mappercustom.OmsOrderDao;
import mall.fruit.devil.mappercustom.OmsOrderOperateHistoryDao;
import mall.fruit.devil.entitycustom.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private OmsOrderMapper omsOrderMapper;
    @Autowired
    private OmsOrderDao omsOrderDao;


    @Autowired
    private OmsOrderOperateHistoryDao omsOrderOperateHistoryDao;

    @Autowired
    private OmsOrderOperateHistoryMapper omsOrderOperateHistoryMapper;

    @ApiOperation("查询订单")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<OmsOrder>> list(OmsOrderQueryParam omsOrderQueryParam,
                                                   @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){

        Page orderDetailPage = new Page<OmsOrder>(pageNum,pageSize);
        Page<OmsOrder> omsOrderPage = (Page<OmsOrder>) omsOrderDao.getList(orderDetailPage,omsOrderQueryParam);
        return CommonResult.success(CommonPage.restPage(omsOrderPage));
    }

    @ApiOperation("批量发货")
    @RequestMapping(value = "/update/delivery", method = RequestMethod.POST)
    public CommonResult delivery(@RequestBody List<OmsOrderDeliveryParam> deliveryParamList){

        int count = omsOrderDao.delivery(deliveryParamList);
        List <OmsOrderOperateHistory> omsOrderOperateHistoryList = deliveryParamList.stream()
                .map(omsOrderDeliveryParam -> {
                    OmsOrderOperateHistory omsOrderOperateHistory = new OmsOrderOperateHistory();
                    omsOrderOperateHistory.setOrderId(omsOrderDeliveryParam.getOrderId());
                    omsOrderOperateHistory.setCreateTime(LocalDateTime.now());
                    omsOrderOperateHistory.setOperateMan("后台管理员");
                    omsOrderOperateHistory.setOrderStatus(2);
                    omsOrderOperateHistory.setNote("完成发货");
                    return omsOrderOperateHistory;
                }).collect(Collectors.toList());
        omsOrderOperateHistoryDao.insertList(omsOrderOperateHistoryList);
        if(count > 0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量关闭订单")
    @RequestMapping(value = "/update/close", method = RequestMethod.POST)
    public CommonResult close(@RequestParam("ids") List<Long> ids, @RequestParam String note) {
        OmsOrder omsOrder =new OmsOrder();
        omsOrder.setStatus(4);
        UpdateWrapper<OmsOrder> updateWrapper = new UpdateWrapper<OmsOrder>();
        updateWrapper.eq("delete_status",0);
        updateWrapper.in("id",ids);
        int count = omsOrderMapper.update(omsOrder,updateWrapper);
        List<OmsOrderOperateHistory> historyList = ids.stream().map(orderId -> {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(orderId);
            history.setCreateTime(LocalDateTime.now());
            history.setOperateMan("后台管理员");
            history.setOrderStatus(4);
            history.setNote("订单关闭:"+note);
            return history;
        }).collect(Collectors.toList());
        omsOrderOperateHistoryDao.insertList(historyList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量删除订单")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        OmsOrder omsOrder =new OmsOrder();
        omsOrder.setStatus(1);
        UpdateWrapper<OmsOrder> updateWrapper = new UpdateWrapper<OmsOrder>();
        updateWrapper.eq("delete_status",0);
        updateWrapper.in("id",ids);
        int count = omsOrderMapper.update(omsOrder,updateWrapper);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation("获取订单详情：订单信息、商品信息、操作记录")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<OmsOrderDetail> detail(@PathVariable Long id) {
        OmsOrderDetail orderDetailResult = omsOrderDao.getDetail(id);
        return CommonResult.success(orderDetailResult);
    }

    @ApiOperation("修改收货人信息")
    @RequestMapping(value = "/update/receiverInfo", method = RequestMethod.POST)
    public CommonResult updateReceiverInfo(@RequestBody OmsReceiverInfoParam receiverInfoParam) {
        OmsOrder order = new OmsOrder();
        order.setId(receiverInfoParam.getOrderId());
        order.setReceiverName(receiverInfoParam.getReceiverName());
        order.setReceiverPhone(receiverInfoParam.getReceiverPhone());
        order.setReceiverPostCode(receiverInfoParam.getReceiverPostCode());
        order.setReceiverDetlAddress(receiverInfoParam.getReceiverDetailAddress());
        order.setReceiverProvince(receiverInfoParam.getReceiverProvince());
        order.setReceiverCity(receiverInfoParam.getReceiverCity());
        order.setReceiverRegion(receiverInfoParam.getReceiverRegion());
        order.setModifyTime(LocalDateTime.now());
        int count = omsOrderMapper.updateById(order);
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(receiverInfoParam.getOrderId());
        history.setCreateTime(LocalDateTime.now());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(receiverInfoParam.getStatus());
        history.setNote("修改收货人信息");
        omsOrderOperateHistoryMapper.insert(history);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改订单费用信息")
    @RequestMapping(value = "/update/moneyInfo", method = RequestMethod.POST)
    public CommonResult updateReceiverInfo(@RequestBody OmsMoneyInfoParam moneyInfoParam) {
        OmsOrder order = new OmsOrder();
        order.setId(moneyInfoParam.getOrderId());
        order.setFreightAmount(moneyInfoParam.getFreightAmount());
        order.setDiscountAmount(moneyInfoParam.getDiscountAmount());
        order.setModifyTime(LocalDateTime.now());
        int count = omsOrderMapper.updateById(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(moneyInfoParam.getOrderId());
        history.setCreateTime(LocalDateTime.now());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(moneyInfoParam.getStatus());
        history.setNote("修改费用信息");
        omsOrderOperateHistoryMapper.insert(history);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("备注订单")
    @RequestMapping(value = "/update/note", method = RequestMethod.POST)
    public CommonResult updateNote(@RequestParam("id") Long id,
                                   @RequestParam("note") String note,
                                   @RequestParam("status") Integer status) {
        OmsOrder order = new OmsOrder();
        order.setId(id);
        order.setNote(note);
        order.setModifyTime(LocalDateTime.now());
        int count = omsOrderMapper.updateById(order);
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(id);
        history.setCreateTime(LocalDateTime.now());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(status);
        history.setNote("修改备注信息："+note);
        omsOrderOperateHistoryMapper.insert(history);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
