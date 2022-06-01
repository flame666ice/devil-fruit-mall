package mall.fruit.devil.devilmbg.mappercustom;

import mall.fruit.devil.devilmbg.entity.SmsCouponProductRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券和商品关系自定义Dao
 * Created by macro on 2018/8/28.
 */
public interface SmsCouponProductRelationDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<SmsCouponProductRelation> productRelationList);
}
