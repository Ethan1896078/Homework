package team.t9001.saad.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import team.t9001.saad.common.Page;
import team.t9001.saad.model.Notice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/17.
 */
@Repository
public class NoticeDao extends BaseDao {
    private RowMapper<Notice> noticeMapper = new RowMapper<Notice>() {

        public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
            Notice notice = new Notice();
            notice.setNoticeId(rs.getInt("notice_id"));
            notice.setTitle(rs.getString("title"));
            notice.setTitle(rs.getString("content"));
            notice.setAuthor(rs.getString("author"));
            notice.setPublishTime(rs.getString("publish_time"));
            notice.setStatus(rs.getInt("status"));
            return notice;
        }
    };

    /**
     * 获取公告列表
     * @return
     */
    public List<Notice> getNoticeList(Page page) {
        int pageSize = page.getPageSize();
        int beginIndex = page.getCurrentPage() * pageSize;

        String sql = "SELECT * FROM t_notice WHERE status = 1 LIMIT ?, ?";
        List<Notice> noticeList = jdbc.query(sql, noticeMapper, beginIndex, pageSize);

        String countSql = "SELECT COUNT(0) FROM t_notice";
        int totalCount = jdbc.queryForObject(countSql, Integer.class);
        int totalPage = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);

        return noticeList;
    }

    /**
     * 添加公告
     * @param notice
     * @return
     */
    public int addNotice(Notice notice) {
        String sql = "INSERT INTO t_notice (title, content, author, publish_time, status) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbc.update(sql, notice.getTitle(), notice.getContent(), notice.getAuthor(), notice.getPublishTime(), notice.getStatus());
    }

    /**
     * 修改公告
     * @param notice
     * @return
     */
    public int modifyNotice(Notice notice) {
        String sql = "UPDATE t_notice SET title = ?, content = ?, author = ?, publish_time = ?, status = ? " +
                "WHERE notice_id = ?";
        return jdbc.update(sql, notice.getTitle(), notice.getContent(), notice.getAuthor(), notice.getPublishTime()
                , notice.getStatus(), notice.getNoticeId());
    }

    /**
     * 删除公告
     * @param noticeId
     * @return
     */
    public int removeNotice(Integer noticeId) {
        String sql = "UPDATE t_notice SET status = 0 WHERE notice_id = ?";
        return jdbc.update(sql, noticeId);
    }

    /**
     * 根据公告id获取公告信息
     * @param noticeId
     * @return
     */
    public Notice getNoticeInfoById(Integer noticeId) {
        String sql = "SELECT * FROM t_notice WHERE notice_id = ? AND status = 1";
        List<Notice> noticeList = jdbc.query(sql, noticeMapper, noticeId);
        if (noticeList != null && noticeList.size() > 0) {
            return noticeList.get(0);
        }
        return null;
    }
}
