package team.t9001.saad.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import team.t9001.saad.common.Page;
import team.t9001.saad.model.News;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/17.
 */
@Repository
public class NewsDao extends BaseDao {
    private RowMapper<News> newsMapper = new RowMapper<News>() {

        public News mapRow(ResultSet rs, int rowNum) throws SQLException {
            News news = new News();
            news.setNewsId(rs.getInt("news_id"));
            news.setTitle(rs.getString("title"));
            news.setTitle(rs.getString("content"));
            news.setAuthor(rs.getString("author"));
            news.setPublishTime(rs.getString("publish_time"));
            news.setStatus(rs.getInt("status"));
            return news;
        }
    };

    /**
     * 获取新闻列表
     * @return
     */
    public List<News> getNewsList(Page page) {
        int pageSize = page.getPageSize();
        int beginIndex = page.getCurrentPage() * pageSize;

        String sql = "SELECT * FROM t_news WHERE status = 1 LIMIT ?, ?";
        List<News> newsList = jdbc.query(sql, newsMapper, beginIndex, pageSize);

        String countSql = "SELECT COUNT(0) FROM t_news";
        int totalCount = jdbc.queryForObject(countSql, Integer.class);
        int totalPage = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);

        return newsList;
    }

    /**
     * 添加新闻
     * @param news
     * @return
     */
    public int addNews(News news) {
        String sql = "INSERT INTO t_news (title, content, author, publish_time, status) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbc.update(sql, news.getTitle(), news.getContent(), news.getAuthor(), news.getPublishTime(), news.getStatus());
    }

    /**
     * 修改新闻
     * @param news
     * @return
     */
    public int modifyNews(News news) {
        String sql = "UPDATE t_news SET title = ?, content = ?, author = ?, publish_time = ?, status = ? " +
                "WHERE news_id = ?";
        return jdbc.update(sql, news.getTitle(), news.getContent(), news.getAuthor(), news.getPublishTime()
                , news.getStatus(), news.getNewsId());
    }

    /**
     * 删除新闻
     * @param newsId
     * @return
     */
    public int removeNews(Integer newsId) {
        String sql = "UPDATE t_news SET status = 0 WHERE news_id = ?";
        return jdbc.update(sql, newsId);
    }

    /**
     * 根据新闻id获取新闻信息
     * @param newsId
     * @return
     */
    public News getNewsInfoById(Integer newsId) {
        String sql = "SELECT * FROM t_news WHERE news_id = ? AND status = 1";
        List<News> newsList = jdbc.query(sql, newsMapper, newsId);
        if (newsList != null && newsList.size() > 0) {
            return newsList.get(0);
        }
        return null;
    }
}
