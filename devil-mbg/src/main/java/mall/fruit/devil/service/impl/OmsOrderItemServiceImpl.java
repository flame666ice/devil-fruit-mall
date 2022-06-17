package mall.fruit.devil.service.impl;

import mall.fruit.devil.entity.OmsOrderItem;
import mall.fruit.devil.mapper.OmsOrderItemMapper;
import mall.fruit.devil.service.IOmsOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单中所包含的商品 服务实现类
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@Service
public class OmsOrderItemServiceImpl extends ServiceImpl<OmsOrderItemMapper, OmsOrderItem> implements IOmsOrderItemService {

}
