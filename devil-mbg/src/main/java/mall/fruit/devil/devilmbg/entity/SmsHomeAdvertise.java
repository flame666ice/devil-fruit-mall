package mall.fruit.devil.devilmbg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 首页轮播广告表
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@TableName("sms_home_advertise")
@ApiModel(value = "SmsHomeAdvertise对象", description = "首页轮播广告表")
public class SmsHomeAdvertise implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    @ApiModelProperty("轮播位置：0->PC首页轮播；1->app首页轮播")
    private Integer type;

    private String pic;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ApiModelProperty("上下线状态：0->下线；1->上线")
    private Integer status;

    @ApiModelProperty("点击数")
    private Integer clickCount;

    @ApiModelProperty("下单数")
    private Integer orderCount;

    @ApiModelProperty("链接地址")
    private String url;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty("排序")
    private Integer sort;


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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "SmsHomeAdvertise{" +
        "id=" + id +
        ", name=" + name +
        ", type=" + type +
        ", pic=" + pic +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", status=" + status +
        ", clickCount=" + clickCount +
        ", orderCount=" + orderCount +
        ", url=" + url +
        ", note=" + note +
        ", sort=" + sort +
        "}";
    }
}
