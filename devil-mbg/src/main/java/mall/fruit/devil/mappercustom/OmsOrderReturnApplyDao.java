package mall.fruit.devil.mappercustom;

import com.baomidou.mybatisplus.core.metadata.IPage;
import mall.fruit.devil.entitycustom.OmsOrderReturnApplyResult;
import mall.fruit.devil.entitycustom.OmsReturnApplyQueryParam;
import mall.fruit.devil.entity.OmsOrderReturnApply;
import org.apache.ibatis.annotations.Param;


public interface OmsOrderReturnApplyDao {
    /**
     * 查询申请列表
     */
    IPage<OmsOrderReturnApply>  getList(IPage<OmsOrderReturnApply> page, @Param("queryParam") OmsReturnApplyQueryParam queryParam);

    /**
     * 获取申请详情
     */
    OmsOrderReturnApplyResult getDetail(@Param("id") Long id);
}
