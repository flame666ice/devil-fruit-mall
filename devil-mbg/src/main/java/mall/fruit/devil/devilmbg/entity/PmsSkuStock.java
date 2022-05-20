package mall.fruit.devil.devilmbg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * sku的库存
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@TableName("pms_sku_stock")
@ApiModel(value = "PmsSkuStock对象", description = "sku的库存")
public class PmsSkuStock implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    @ApiModelProperty("sku编工序")
    private String skuCode;

    private BigDecimal price;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("预警库存")
    private Integer lowStock;

    @ApiModelProperty("展示图片")
    private String pic;

    @ApiModelProperty("销量")
    private Integer sale;

    @ApiModelProperty("单品促销价格")
    private BigDecimal promotionPrice;

    @ApiModelProperty("锁定库存")
    private Integer lockStock;

    @ApiModelProperty("商品销售属性，json格式")
    private String spData;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getLowStock() {
        return lowStock;
    }

    public void setLowStock(Integer lowStock) {
        this.lowStock = lowStock;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getLockStock() {
        return lockStock;
    }

    public void setLockStock(Integer lockStock) {
        this.lockStock = lockStock;
    }

    public String getSpData() {
        return spData;
    }

    public void setSpData(String spData) {
        this.spData = spData;
    }

    @Override
    public String toString() {
        return "PmsSkuStock{" +
        "id=" + id +
        ", productId=" + productId +
        ", skuCode=" + skuCode +
        ", price=" + price +
        ", stock=" + stock +
        ", lowStock=" + lowStock +
        ", pic=" + pic +
        ", sale=" + sale +
        ", promotionPrice=" + promotionPrice +
        ", lockStock=" + lockStock +
        ", spData=" + spData +
        "}";
    }
}
