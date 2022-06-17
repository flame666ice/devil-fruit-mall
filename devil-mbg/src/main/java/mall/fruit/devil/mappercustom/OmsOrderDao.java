package mall.fruit.devil.mappercustom;

import com.baomidou.mybatisplus.core.metadata.IPage;
import mall.fruit.devil.entity.OmsOrder;
import mall.fruit.devil.entitycustom.OmsOrderDeliveryParam;
import mall.fruit.devil.entitycustom.OmsOrderDetail;
import mall.fruit.devil.entitycustom.OmsOrderQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface    OmsOrderDao {

    /**
     * 条件查询订单
     */
    IPage<OmsOrder> getList(IPage<OmsOrder> page, @Param("queryParam") OmsOrderQueryParam queryParam);

    /**
     * 批量发货
     */
    int delivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * 获取订单详情
     */
    OmsOrderDetail getDetail(@Param("id") Long id);
}
