package team.t9001.saad.model;

/**
 * desc:
 * Created by huangzhe on 2016/12/18.
 */
public class Project {
    private int projectId;
    private String projectName;
    private String bioClass;
    private String leader;
    private String content;
    private String progress;
    private String photos;
    private String publishArticleTitles;
    private String publishArticleLinks;
    private String relatedArticleTitles;
    private String relatedArticleLinks;
    private int status;

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", bioClass='" + bioClass + '\'' +
                ", leader='" + leader + '\'' +
                ", content='" + content + '\'' +
                ", progress=" + progress +
                ", photos='" + photos + '\'' +
                ", publishArticleTitles='" + publishArticleTitles + '\'' +
                ", publishArticleLinks='" + publishArticleLinks + '\'' +
                ", relatedArticleTitles='" + relatedArticleTitles + '\'' +
                ", relatedArticleLinks='" + relatedArticleLinks + '\'' +
                ", status=" + status +
                '}';
    }

    public Project() {
        status = 1;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBioClass() {
        return bioClass;
    }

    public void setBioClass(String bioClass) {
        this.bioClass = bioClass;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getPublishArticleTitles() {
        return publishArticleTitles;
    }

    public void setPublishArticleTitles(String publishArticleTitles) {
        this.publishArticleTitles = publishArticleTitles;
    }

    public String getPublishArticleLinks() {
        return publishArticleLinks;
    }

    public void setPublishArticleLinks(String publishArticleLinks) {
        this.publishArticleLinks = publishArticleLinks;
    }

    public String getRelatedArticleTitles() {
        return relatedArticleTitles;
    }

    public void setRelatedArticleTitles(String relatedArticleTitles) {
        this.relatedArticleTitles = relatedArticleTitles;
    }

    public String getRelatedArticleLinks() {
        return relatedArticleLinks;
    }

    public void setRelatedArticleLinks(String relatedArticleLinks) {
        this.relatedArticleLinks = relatedArticleLinks;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
