package mall.fruit.devil.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 专题分类表
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@TableName("cms_subject_category")
@ApiModel(value = "CmsSubjectCategory对象", description = "专题分类表")
public class CmsSubjectCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    @ApiModelProperty("分类图标")
    private String icon;

    @ApiModelProperty("专题数量")
    private Integer subjectCount;

    private Integer showStatus;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSubjectCount() {
        return subjectCount;
    }

    public void setSubjectCount(Integer subjectCount) {
        this.subjectCount = subjectCount;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "CmsSubjectCategory{" +
        "id=" + id +
        ", name=" + name +
        ", icon=" + icon +
        ", subjectCount=" + subjectCount +
        ", showStatus=" + showStatus +
        ", sort=" + sort +
        "}";
    }
}
