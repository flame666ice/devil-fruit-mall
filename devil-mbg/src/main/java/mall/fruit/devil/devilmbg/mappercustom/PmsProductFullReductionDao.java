package mall.fruit.devil.devilmbg.mappercustom;

import mall.fruit.devil.devilmbg.entity.PmsProductFullReduction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品满减自定义Dao
 * Created by macro on 2018/4/26.
 */
public interface PmsProductFullReductionDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<PmsProductFullReduction> productFullReductionList);
}
