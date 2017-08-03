package cn.wow.common.domain;

import java.util.Date;

/**
 * 会员组
 * @author zhenjunzhuo
 * 2017年7月13日
 */
public class Group {
    private Long id;
    // 名称
    private String name;
    // 创建时间
    private Date createTime;

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
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}