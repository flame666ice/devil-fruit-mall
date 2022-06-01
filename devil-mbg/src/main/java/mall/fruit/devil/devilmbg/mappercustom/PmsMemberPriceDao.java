package mall.fruit.devil.devilmbg.mappercustom;

import mall.fruit.devil.devilmbg.entity.PmsMemberPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员价格管理自定义Dao
 * Created by macro on 2018/4/26.
 */
public interface PmsMemberPriceDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<PmsMemberPrice> memberPriceList);
}
