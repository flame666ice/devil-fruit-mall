package mall.fruit.devil.devilmbg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 优选专区
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@TableName("cms_preference_area")
@ApiModel(value = "CmsPreferenceArea对象", description = "优选专区")
public class CmsPreferenceArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String subTitle;

    @ApiModelProperty("展示图片")
    private byte[] pic;

    private Integer sort;

    private Integer showstatus;


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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getShowstatus() {
        return showstatus;
    }

    public void setShowstatus(Integer showstatus) {
        this.showstatus = showstatus;
    }

    @Override
    public String toString() {
        return "CmsPreferenceArea{" +
        "id=" + id +
        ", name=" + name +
        ", subTitle=" + subTitle +
        ", pic=" + pic +
        ", sort=" + sort +
        ", showstatus=" + showstatus +
        "}";
    }
}
