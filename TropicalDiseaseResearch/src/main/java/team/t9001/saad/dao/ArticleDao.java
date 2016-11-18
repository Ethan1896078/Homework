package team.t9001.saad.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import team.t9001.saad.model.Article;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/17.
 */
@Repository
public class ArticleDao extends BaseDao {
    private RowMapper<Article> articleMapper = new RowMapper<Article>() {

        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            Article article = new Article();
            article.setArticleId(rs.getInt("article_id"));
            article.setTitle(rs.getString("title"));
            article.setAuthor(rs.getString("author"));
            article.setPublishTime(rs.getString("publish_time"));
            article.setExternalLink(rs.getString("external_link"));
            article.setStatus(rs.getInt("status"));
            return article;
        }
    };

    /**
     * 获取文章列表
     * @return
     */
    public List<Article> getArticleList() {
        String sql = "SELECT * FROM t_article";
        List<Article> articleList = jdbc.query(sql, articleMapper);
        return articleList;
    }

    /**
     * 添加文章
     * @param article
     * @return
     */
    public int addArticle(Article article) {
        String sql = "INSERT INTO t_article (title, author, publish_time, external_link, status) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbc.update(sql, article.getTitle(), article.getAuthor(), article.getPublishTime(), article.getExternalLink(), article.getStatus());
    }
}
