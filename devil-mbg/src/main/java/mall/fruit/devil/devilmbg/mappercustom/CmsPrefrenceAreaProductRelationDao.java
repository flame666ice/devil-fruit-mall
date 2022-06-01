package mall.fruit.devil.devilmbg.mappercustom;

import mall.fruit.devil.devilmbg.entity.CmsPreferenceAreaProductRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优选和商品关系自定义Dao
 * Created by macro on 2018/4/26.
 */
public interface CmsPrefrenceAreaProductRelationDao {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<CmsPreferenceAreaProductRelation> prefrenceAreaProductRelationList);
}
