package team.t9001.saad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import team.t9001.saad.common.Page;
import team.t9001.saad.model.Notice;

/**
 * desc:
 * Created by lifeihu on 2016/11/17.
 */
@Repository
public class NoticeDao extends BaseDao {
	
    private RowMapper<Notice> noticeMapper = new RowMapper<Notice>() {

        public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Notice notice = new Notice();
        	notice.setNoticeId(rs.getInt("notice_id"));
        	notice.setNoticeTitle(rs.getString("notice_title"));
        	notice.setNoticeContext(rs.getString("notice_context"));
        	notice.setCreateDate(rs.getDate("create_date"));
            return notice;
        }
    };

    /**
     * 获取列表
     * @return
     */
    public List<Notice> getList(Page page) {
        int pageSize = page.getPageSize();
        int beginIndex = page.getCurrentPage() * pageSize;

        String sql = "SELECT notice_id, notice_title, create_date FROM t_notice LIMIT ?, ?";
        List<Notice> list = jdbc.query(sql, noticeMapper, beginIndex, pageSize);

        String countSql = "SELECT COUNT(0) FROM t_notice";
        int totalCount = jdbc.queryForObject(countSql, Integer.class);
        int totalPage = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);

        return list;
    }
    
    public Notice getById(Integer noticeId){
    	String sql = "SELECT * FROM t_notice WHERE notice_id = ?";
    	Notice notice = jdbc.queryForObject(sql, noticeMapper, noticeId);
        return notice;
    }

    /**
     * 添加
     * @param 
     * @return
     */
    public int add(Notice notice) {
        String sql = "INSERT INTO t_notice (notice_title, notice_context, create_date) " +
                "VALUES (?, ?, now())";
        return jdbc.update(sql, notice.getNoticeTitle(), notice.getNoticeContext());
    }

    /**
     * 修改
     * @param notice
     * @return
     */
    public int modify(Notice notice) {
        String sql = "UPDATE t_notice SET notice_title = ?, notice_context = ?, create_date = now() " +
                "WHERE notice_id = ?";
        return jdbc.update(sql, notice.getNoticeTitle(), notice.getNoticeContext(), notice.getNoticeId());
    }

    /**
     * 删除
     * @param 
     * @return
     */
    public int remove(Integer noticeId) {
        String sql = "DELETE FROM t_notice WHERE notice_id = ?";
        return jdbc.update(sql, noticeId);
    }
}
