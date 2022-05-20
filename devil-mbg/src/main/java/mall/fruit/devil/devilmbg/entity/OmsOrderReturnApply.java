package mall.fruit.devil.devilmbg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 订单退货申请
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@TableName("oms_order_return_apply")
@ApiModel(value = "OmsOrderReturnApply对象", description = "订单退货申请")
public class OmsOrderReturnApply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("收货地址表id")
    private Long companyAddressId;

    @ApiModelProperty("退货商品id")
    private Long productId;

    @ApiModelProperty("订单编号")
    private String orderSn;

    @ApiModelProperty("申请时间")
    private LocalDateTime createTime;

    @ApiModelProperty("会员用户名")
    private String memberUsername;

    @ApiModelProperty("退款金额")
    private BigDecimal returnAmount;

    @ApiModelProperty("退货人姓名")
    private String returnName;

    @ApiModelProperty("退货人电话")
    private String returnPhone;

    @ApiModelProperty("申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    private Integer status;

    @ApiModelProperty("处理时间")
    private LocalDateTime handleTime;

    @ApiModelProperty("商品图片")
    private String productPic;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品品牌")
    private String productBrand;

    @ApiModelProperty("商品销售属性：装饰：红色；尺工序：xl;")
    private String productAttr;

    @ApiModelProperty("退货数量")
    private Integer productCount;

    @ApiModelProperty("商品单价")
    private BigDecimal productPrice;

    @ApiModelProperty("商品实际支付单价")
    private BigDecimal productRealPrice;

    @ApiModelProperty("原因")
    private String reason;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("凭证图片，以逗号隔开")
    private String proofPics;

    @ApiModelProperty("处理备注")
    private String handleNote;

    @ApiModelProperty("处理人员")
    private String handleMan;

    @ApiModelProperty("收货人")
    private String receiveMan;

    @ApiModelProperty("收货时间")
    private LocalDateTime receiveTime;

    @ApiModelProperty("收货备注")
    private String receiveNote;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCompanyAddressId() {
        return companyAddressId;
    }

    public void setCompanyAddressId(Long companyAddressId) {
        this.companyAddressId = companyAddressId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    public String getReturnName() {
        return returnName;
    }

    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }

    public String getReturnPhone() {
        return returnPhone;
    }

    public void setReturnPhone(String returnPhone) {
        this.returnPhone = returnPhone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(LocalDateTime handleTime) {
        this.handleTime = handleTime;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductAttr() {
        return productAttr;
    }

    public void setProductAttr(String productAttr) {
        this.productAttr = productAttr;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductRealPrice() {
        return productRealPrice;
    }

    public void setProductRealPrice(BigDecimal productRealPrice) {
        this.productRealPrice = productRealPrice;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProofPics() {
        return proofPics;
    }

    public void setProofPics(String proofPics) {
        this.proofPics = proofPics;
    }

    public String getHandleNote() {
        return handleNote;
    }

    public void setHandleNote(String handleNote) {
        this.handleNote = handleNote;
    }

    public String getHandleMan() {
        return handleMan;
    }

    public void setHandleMan(String handleMan) {
        this.handleMan = handleMan;
    }

    public String getReceiveMan() {
        return receiveMan;
    }

    public void setReceiveMan(String receiveMan) {
        this.receiveMan = receiveMan;
    }

    public LocalDateTime getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(LocalDateTime receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getReceiveNote() {
        return receiveNote;
    }

    public void setReceiveNote(String receiveNote) {
        this.receiveNote = receiveNote;
    }

    @Override
    public String toString() {
        return "OmsOrderReturnApply{" +
        "id=" + id +
        ", orderId=" + orderId +
        ", companyAddressId=" + companyAddressId +
        ", productId=" + productId +
        ", orderSn=" + orderSn +
        ", createTime=" + createTime +
        ", memberUsername=" + memberUsername +
        ", returnAmount=" + returnAmount +
        ", returnName=" + returnName +
        ", returnPhone=" + returnPhone +
        ", status=" + status +
        ", handleTime=" + handleTime +
        ", productPic=" + productPic +
        ", productName=" + productName +
        ", productBrand=" + productBrand +
        ", productAttr=" + productAttr +
        ", productCount=" + productCount +
        ", productPrice=" + productPrice +
        ", productRealPrice=" + productRealPrice +
        ", reason=" + reason +
        ", description=" + description +
        ", proofPics=" + proofPics +
        ", handleNote=" + handleNote +
        ", handleMan=" + handleMan +
        ", receiveMan=" + receiveMan +
        ", receiveTime=" + receiveTime +
        ", receiveNote=" + receiveNote +
        "}";
    }
}
