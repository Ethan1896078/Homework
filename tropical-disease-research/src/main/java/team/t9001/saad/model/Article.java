package team.t9001.saad.model;

/**
 * desc:发表文章
 * Created by huangzhe on 2016/11/16.
 */
public class Article {
    /** 文章编号 */
    private Integer articleId;
    /** 主题 */
    private String title;
    /** 作者 */
    private String author;
    /** 发表时间 */
    private String publishTime;
    /** 外部链接 */
    private String externalLink;
    /** 状态 */
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }


    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", externalLink='" + externalLink + '\'' +
                ", status=" + status +
                '}';
    }
}
