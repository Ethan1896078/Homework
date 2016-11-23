package team.t9001.saad.model;

import java.sql.Date;

import com.alibaba.fastjson.JSON;

/**
 * desc:工具
 * Created by lifeihu on 2016/11/16.
 */
public class Tools {

	private Integer toolsId;
    /** 类型 */
    private String toolsType;
    /** 标题  */
    private String toolsTitle;
    /** 链接 */
    private String toolsUrl;
    /** 创建时间 */
    private Date createDate;

    public Tools() {
		super();
	}

	public Tools(Integer toolsId, String toolsType, String toolsTitle,
			String toolsUrl, Date createDate) {
		super();
		this.toolsId = toolsId;
		this.toolsType = toolsType;
		this.toolsTitle = toolsTitle;
		this.toolsUrl = toolsUrl;
		this.createDate = createDate;
	}

	public Integer getToolsId() {
		return toolsId;
	}

	public void setToolsId(Integer toolsId) {
		this.toolsId = toolsId;
	}

	public String getToolsType() {
		return toolsType;
	}

	public void setToolsType(String toolsType) {
		this.toolsType = toolsType;
	}

	public String getToolsTitle() {
		return toolsTitle;
	}

	public void setToolsTitle(String toolsTitle) {
		this.toolsTitle = toolsTitle;
	}

	public String getToolsUrl() {
		return toolsUrl;
	}

	public void setToolsUrl(String toolsUrl) {
		this.toolsUrl = toolsUrl;
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
