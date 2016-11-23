package team.t9001.saad.model;

import java.sql.Date;

import com.alibaba.fastjson.JSON;

/**
 * desc:新闻动态
 * Created by lifeihu on 2016/11/16.
 */
public class News {

	private Integer newsId;
    /** 标题 */
    private String newsTitle;
    /** 内容 */
    private String newsContext;
    /** 创建时间 */
    private Date createDate;

    public News() {
		super();
	}

	public News(Integer newsId, String newsTitle, String newsContext,
			Date createDate) {
		super();
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsContext = newsContext;
		this.createDate = createDate;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContext() {
		return newsContext;
	}

	public void setNewsContext(String newsContext) {
		this.newsContext = newsContext;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
