package mall.fruit.devil.devilmbg.mappercustom;

import com.baomidou.mybatisplus.core.metadata.IPage;
import mall.fruit.devil.devilmbg.entity.OmsOrder;
import mall.fruit.devil.devilmbg.entitycustom.OmsOrderReturnApplyResult;
import mall.fruit.devil.devilmbg.entitycustom.OmsReturnApplyQueryParam;
import mall.fruit.devil.devilmbg.entity.OmsOrderReturnApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;


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
