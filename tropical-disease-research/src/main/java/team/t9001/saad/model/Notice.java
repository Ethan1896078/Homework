package team.t9001.saad.model;

/**
 * desc:公告动态
 * Created by huangzhe on 2016/11/16.
 */
public class Notice {
    /** 公告编号 */
    private int noticeId;
    /** 主题 */
    private String title;
    /** 内容 */
    private String content;

    /** 作者 */

    private String author;

    /** 发表时间 */
    private String publishTime;
    /** 状态 */
    private int status;
    public Notice() {
        status = 1;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeId=" + noticeId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", status=" + status +
                '}';
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
