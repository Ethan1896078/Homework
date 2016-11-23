package team.t9001.saad.model;

import java.sql.Date;

import com.alibaba.fastjson.JSON;

/**
 * desc:通知
 * Created by lifeihu on 2016/11/16.
 */
public class Notice {

	private Integer noticeId;
    /** 标题 */
    private String noticeTitle;
    /** 内容 */
    private String noticeContext;
    /** 创建时间 */
    private Date createDate;

    public Notice() {
		super();
	}

	public Notice(Integer noticeId, String noticeTitle, String noticeContext,
			Date createDate) {
		super();
		this.noticeId = noticeId;
		this.noticeTitle = noticeTitle;
		this.noticeContext = noticeContext;
		this.createDate = createDate;
	}

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContext() {
		return noticeContext;
	}

	public void setNoticeContext(String noticeContext) {
		this.noticeContext = noticeContext;
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
