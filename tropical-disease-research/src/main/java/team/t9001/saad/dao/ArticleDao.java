package team.t9001.saad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import team.t9001.saad.common.Page;
import team.t9001.saad.model.Article;

/**
 * desc:
 * Created by huangzhe on 2016/11/17.
 */
@Repository
public class ArticleDao extends BaseDao {
    private RowMapper<Article> articleMapper = new RowMapper<Article>() {

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
    public List<Article> getArticleList(Page page) {
        int pageSize = page.getPageSize();
        int beginIndex = page.getCurrentPage() * pageSize;

        String sql = "SELECT * FROM t_article LIMIT ?, ?";
        List<Article> articleList = jdbc.query(sql, articleMapper, beginIndex, pageSize);

        String countSql = "SELECT COUNT(0) FROM t_article";
        int totalCount = jdbc.queryForObject(countSql, Integer.class);
        int totalPage = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);

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

    /**
     * 修改文章
     * @param article
     * @return
     */
    public int modifyArticle(Article article) {
        String sql = "UPDATE t_article SET title = ?, author = ?, publish_time = ?, external_link = ?, status = ?" +
                "WHERE article_id = ?";
        return jdbc.update(sql, article.getTitle(), article.getAuthor(), article.getPublishTime()
                , article.getExternalLink(), article.getStatus(), article.getArticleId());
    }

    /**
     * 删除文章
     * @param articleId
     * @return
     */
    public int removeArticle(Integer articleId) {
        String sql = "DELETE FROM t_article WHERE articleId = ?";
        return jdbc.update(sql, articleId);
    }
    
    /**
     * 获取统计文章列表
     * @return
     */
    public List<Article> getAnalysisArticleList(Page page, String analysisType, String analysisValue) {
        int pageSize = page.getPageSize();
        int beginIndex = page.getCurrentPage() * pageSize;

        String whereSql = null;
        if("author".equals(analysisType)){
        	whereSql = " WHERE author = ?";
    	}else if("magazine".equals(analysisType)){
    		whereSql = " WHERE magazine = ?";
    	}else if("year".equals(analysisType)){
    		whereSql = " WHERE left(publish_time, 4) = ?";
    	}else {
    		return new ArrayList<Article>();
    	}
        String sql = "SELECT article_id, title, author, magazine, publish_time FROM t_article " + whereSql + " LIMIT ?, ?";
        List<Article> articleList = jdbc.query(sql, articleMapper, analysisValue, beginIndex, pageSize);

        String countSql = "SELECT COUNT(0) FROM t_article" + whereSql;
        int totalCount = jdbc.queryForObject(countSql, Integer.class, analysisValue);
        int totalPage = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);

        return articleList;
    }
}
