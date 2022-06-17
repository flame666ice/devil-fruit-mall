package mall.fruit.devil.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用户标签表
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@TableName("ums_member_tag")
@ApiModel(value = "UmsMemberTag对象", description = "用户标签表")
public class UmsMemberTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    @ApiModelProperty("自动打标签完成订单数量")
    private Integer finishOrderCount;

    @ApiModelProperty("自动打标签完成订单金额")
    private BigDecimal finishOrderAmount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFinishOrderCount() {
        return finishOrderCount;
    }

    public void setFinishOrderCount(Integer finishOrderCount) {
        this.finishOrderCount = finishOrderCount;
    }

    public BigDecimal getFinishOrderAmount() {
        return finishOrderAmount;
    }

    public void setFinishOrderAmount(BigDecimal finishOrderAmount) {
        this.finishOrderAmount = finishOrderAmount;
    }

    @Override
    public String toString() {
        return "UmsMemberTag{" +
        "id=" + id +
        ", name=" + name +
        ", finishOrderCount=" + finishOrderCount +
        ", finishOrderAmount=" + finishOrderAmount +
        "}";
    }
}
