package cn.wow.common.domain;

import java.util.Date;

/**
 * 教育培训
 * @author zhenjunzhuo
 * 2017年7月11日
 */
public class Train {
    private Long id;
    // 标题
    private String title;
    // 创建时间
    private Date createTime;
    // 创建者
    private Long creator;
    // 内容
    private String content;
    // 类型, 1. 培训内容  2. 调查表
    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}