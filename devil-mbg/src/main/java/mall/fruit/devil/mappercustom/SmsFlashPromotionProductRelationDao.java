package mall.fruit.devil.mappercustom;

import com.baomidou.mybatisplus.core.metadata.IPage;
import mall.fruit.devil.entitycustom.SmsFlashPromotionProduct;
import org.apache.ibatis.annotations.Param;

/**
 * 限时购商品关系管理自定义Dao
 * Created by macro on 2018/11/16.
 */
public interface SmsFlashPromotionProductRelationDao {
    /**
     * 获取限时购及相关商品信息
     */
    IPage<SmsFlashPromotionProduct> getList(IPage<SmsFlashPromotionProduct> page, @Param("flashPromotionId") Long flashPromotionId, @Param("flashPromotionSessionId") Long flashPromotionSessionId);
}
