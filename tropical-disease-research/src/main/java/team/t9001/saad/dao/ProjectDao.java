package team.t9001.saad.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import team.t9001.saad.common.Page;
import team.t9001.saad.model.Project;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/17.
 */
@Repository
public class ProjectDao extends BaseDao {
    private RowMapper<Project> projectMapper = new RowMapper<Project>() {

        public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
            Project project = new Project();
            project.setProjectId(rs.getInt("project_id"));
            project.setProjectName(rs.getString("project_name"));
            project.setBioClass(rs.getString("bio_class"));
            project.setLeader(rs.getString("leader"));
            project.setContent(rs.getString("content"));
            project.setProgress(rs.getString("progress"));
            project.setPhotos(rs.getString("photos"));
            project.setPublishArticleTitles(rs.getString("publish_article_titles"));
            project.setPublishArticleLinks(rs.getString("publish_article_links"));
            project.setRelatedArticleTitles(rs.getString("related_article_titles"));
            project.setRelatedArticleLinks(rs.getString("related_article_links"));
            project.setStatus(rs.getInt("status"));
            return project;
        }
    };

    /**
     * 获取项目列表
     * @return
     */
    public List<Project> getProjectList(Page page) {
        int pageSize = page.getPageSize();
        int beginIndex = page.getCurrentPage() * pageSize;

        String sql = "SELECT * FROM t_project WHERE status = 1 LIMIT ?, ?";
        List<Project> projectList = jdbc.query(sql, projectMapper, beginIndex, pageSize);

        String countSql = "SELECT COUNT(0) FROM t_project";
        int totalCount = jdbc.queryForObject(countSql, Integer.class);
        int totalPage = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);

        return projectList;
    }

    /**
     * 添加项目
     * @param project
     * @return
     */
    public int addProject(Project project) {
        String sql = "INSERT INTO t_project (project_name, bio_class, leader, content, progress, photos, " +
                "publish_article_titles, publish_article_links, related_article_titles, related_article_links, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbc.update(sql, project.getProjectName(), project.getBioClass(), project.getLeader(), project.getContent(), project.getProgress()
                , project.getPhotos(), project.getPublishArticleTitles(), project.getPublishArticleLinks()
                , project.getRelatedArticleTitles(), project.getRelatedArticleLinks(), project.getStatus());
    }

    /**
     * 修改项目
     * @param project
     * @return
     */
    public int modifyProject(Project project) {
        String sql = "UPDATE t_project SET project_name = ?, bio_class = ?, leader = ?, content = ?, progress = ?, photos = ?" +
                ", publish_article_titles = ?, publish_article_links = ?, related_article_titles = ? " +
                ", related_article_links = ?, status = ? " +
                "WHERE project_id = ?";
        return jdbc.update(sql, project.getProjectName(), project.getBioClass(), project.getLeader()
                , project.getContent(), project.getProgress(), project.getPhotos()
                , project.getPublishArticleTitles(), project.getPublishArticleLinks()
                , project.getRelatedArticleTitles(), project.getRelatedArticleLinks()
                , project.getStatus(), project.getProjectId());
    }

    /**
     * 删除项目
     * @param projectId
     * @return
     */
    public int removeProject(Integer projectId) {
        String sql = "UPDATE t_project SET status = 0 WHERE project_id = ?";
        return jdbc.update(sql, projectId);
    }

    /**
     * 根据项目id获取项目信息
     * @param projectId
     * @return
     */
    public Project getProjectInfoById(Integer projectId) {
        String sql = "SELECT * FROM t_project WHERE project_id = ? AND status = 1";
        List<Project> projectList = jdbc.query(sql, projectMapper, projectId);
        if (projectList != null && projectList.size() > 0) {
            return projectList.get(0);
        }
        return null;
    }
}
