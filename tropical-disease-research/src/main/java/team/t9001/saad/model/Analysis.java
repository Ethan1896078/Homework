package team.t9001.saad.model;

import java.sql.Date;

import com.alibaba.fastjson.JSON;

/**
 * desc:分析
 * Created by lifeihu on 2016/11/16.
 */
public class Analysis {

    /** 标题 */
    private String title;
    /** 数量 */
    private Integer count;

    public Analysis() {
		super();
	}

	public Analysis(String title, Integer count) {
		super();
		this.title = title;
		this.count = count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
