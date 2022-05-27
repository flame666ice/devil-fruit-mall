package mall.fruit.devil.devilmbg.mappercustom;

import com.baomidou.mybatisplus.core.metadata.IPage;
import mall.fruit.devil.devilmbg.entity.OmsOrder;
import mall.fruit.devil.devilmbg.entitycustom.OmsOrderDeliveryParam;
import mall.fruit.devil.devilmbg.entitycustom.OmsOrderDetail;
import mall.fruit.devil.devilmbg.entitycustom.OmsOrderQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OmsOrderDao {

    /**
     * 条件查询订单
     */
    List<OmsOrder> getList(IPage<OmsOrder> page, @Param("queryParam") OmsOrderQueryParam queryParam);

    /**
     * 批量发货
     */
    int delivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * 获取订单详情
     */
    OmsOrderDetail getDetail(@Param("id") Long id);
}
