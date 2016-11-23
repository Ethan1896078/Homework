package team.t9001.saad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import team.t9001.saad.common.Page;
import team.t9001.saad.model.News;

/**
 * desc:
 * Created by lifeihu on 2016/11/17.
 */
@Repository
public class NewsDao extends BaseDao {
	
    private RowMapper<News> newsMapper = new RowMapper<News>() {

        public News mapRow(ResultSet rs, int rowNum) throws SQLException {
        	News news = new News();
        	news.setNewsId(rs.getInt("news_id"));
        	news.setNewsTitle(rs.getString("news_title"));
        	news.setNewsContext(rs.getString("news_context"));
        	news.setCreateDate(rs.getDate("create_date"));
            return news;
        }
    };

    /**
     * 获取列表
     * @return
     */
    public List<News> getList(Page page) {
        int pageSize = page.getPageSize();
        int beginIndex = page.getCurrentPage() * pageSize;

        String sql = "SELECT news_id, news_title, create_date FROM t_news LIMIT ?, ?";
        List<News> list = jdbc.query(sql, newsMapper, beginIndex, pageSize);

        String countSql = "SELECT COUNT(0) FROM t_news";
        int totalCount = jdbc.queryForObject(countSql, Integer.class);
        int totalPage = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);

        return list;
    }
    
    public News getById(Integer newsId){
    	String sql = "SELECT * FROM t_news WHERE news_id = ?";
    	News news = jdbc.queryForObject(sql, newsMapper, newsId);
        return news;
    }

    /**
     * 添加
     * @param 
     * @return
     */
    public int add(News news) {
        String sql = "INSERT INTO t_news (news_title, news_context, create_date) " +
                "VALUES (?, ?, now())";
        return jdbc.update(sql, news.getNewsTitle(), news.getNewsContext());
    }

    /**
     * 修改
     * @param news
     * @return
     */
    public int modify(News news) {
        String sql = "UPDATE t_news SET news_title = ?, news_context = ?, create_date = now() " +
                "WHERE news_id = ?";
        return jdbc.update(sql, news.getNewsTitle(), news.getNewsContext(), news.getNewsId());
    }

    /**
     * 删除
     * @param 
     * @return
     */
    public int remove(Integer newsId) {
        String sql = "DELETE FROM t_news WHERE news_id = ?";
        return jdbc.update(sql, newsId);
    }
}
