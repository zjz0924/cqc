package cn.wow.common.domain;

import java.util.Date;

/**
 * 新闻类型
 * @author zhenjunzhuo
 * 2017年7月11日
 */
public class NewsType {
    private Long id;
    // 名称
    private String name;
    // 创建时间
    private Date createTime;
    // 是否显示
    private String isShow;

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

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
}