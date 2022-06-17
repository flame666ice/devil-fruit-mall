package mall.fruit.devil.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 优选专区和产品关系表
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@TableName("cms_preference_area_product_relation")
@ApiModel(value = "CmsPreferenceAreaProductRelation对象", description = "优选专区和产品关系表")
public class CmsPreferenceAreaProductRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long preferenceAreaId;

    private Long productId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPreferenceAreaId() {
        return preferenceAreaId;
    }

    public void setPreferenceAreaId(Long preferenceAreaId) {
        this.preferenceAreaId = preferenceAreaId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "CmsPreferenceAreaProductRelation{" +
        "id=" + id +
        ", preferenceAreaId=" + preferenceAreaId +
        ", productId=" + productId +
        "}";
    }
}
