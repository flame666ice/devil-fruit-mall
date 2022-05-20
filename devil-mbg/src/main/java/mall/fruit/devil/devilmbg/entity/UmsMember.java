package mall.fruit.devil.devilmbg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author flame
 * @since 2022-05-20
 */
@TableName("ums_member")
@ApiModel(value = "UmsMember对象", description = "会员表")
public class UmsMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long memberLevelId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密工序")
    private String password;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("宝贝号工序")
    private String phone;

    @ApiModelProperty("帐号启用状态:0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty("注册时间")
    private LocalDateTime createTime;

    @ApiModelProperty("头像")
    private String icon;

    @ApiModelProperty("性别：0->未知；1->男；2->女")
    private Integer gender;

    @ApiModelProperty("生日")
    private LocalDate birthday;

    @ApiModelProperty("所做城市")
    private String city;

    @ApiModelProperty("职业")
    private String job;

    @ApiModelProperty("个性签名")
    private String personalizedSignature;

    @ApiModelProperty("用户来源")
    private Integer sourceType;

    @ApiModelProperty("积分")
    private Integer integration;

    @ApiModelProperty("成长值")
    private Integer growth;

    @ApiModelProperty("剩余抽奖次数")
    private Integer luckeyCount;

    @ApiModelProperty("历史积分数量")
    private Integer historyIntegration;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberLevelId() {
        return memberLevelId;
    }

    public void setMemberLevelId(Long memberLevelId) {
        this.memberLevelId = memberLevelId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPersonalizedSignature() {
        return personalizedSignature;
    }

    public void setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public Integer getLuckeyCount() {
        return luckeyCount;
    }

    public void setLuckeyCount(Integer luckeyCount) {
        this.luckeyCount = luckeyCount;
    }

    public Integer getHistoryIntegration() {
        return historyIntegration;
    }

    public void setHistoryIntegration(Integer historyIntegration) {
        this.historyIntegration = historyIntegration;
    }

    @Override
    public String toString() {
        return "UmsMember{" +
        "id=" + id +
        ", memberLevelId=" + memberLevelId +
        ", username=" + username +
        ", password=" + password +
        ", nickname=" + nickname +
        ", phone=" + phone +
        ", status=" + status +
        ", createTime=" + createTime +
        ", icon=" + icon +
        ", gender=" + gender +
        ", birthday=" + birthday +
        ", city=" + city +
        ", job=" + job +
        ", personalizedSignature=" + personalizedSignature +
        ", sourceType=" + sourceType +
        ", integration=" + integration +
        ", growth=" + growth +
        ", luckeyCount=" + luckeyCount +
        ", historyIntegration=" + historyIntegration +
        "}";
    }
}
