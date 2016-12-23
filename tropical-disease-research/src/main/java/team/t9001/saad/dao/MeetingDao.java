package team.t9001.saad.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import team.t9001.saad.common.Page;
import team.t9001.saad.model.Meeting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/17.
 */
@Repository
public class MeetingDao extends BaseDao {
    private RowMapper<Meeting> meetingMapper = new RowMapper<Meeting>() {

        public Meeting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Meeting meeting = new Meeting();
            meeting.setMeetingId(rs.getInt("meeting_id"));
            meeting.setTitle(rs.getString("title"));
            meeting.setContent(rs.getString("content"));
            meeting.setConveneTime(rs.getString("convene_time"));
            meeting.setStatus(rs.getInt("status"));
            return meeting;
        }
    };

    /**
     * 获取会议列表
     * @return
     */
    public List<Meeting> getMeetingList(Page page) {
        int pageSize = page.getPageSize();
        int beginIndex = page.getCurrentPage() * pageSize;

        String sql = "SELECT * FROM t_meeting WHERE status = 1 LIMIT ?, ?";
        List<Meeting> meetingList = jdbc.query(sql, meetingMapper, beginIndex, pageSize);

        String countSql = "SELECT COUNT(0) FROM t_meeting";
        int totalCount = jdbc.queryForObject(countSql, Integer.class);
        int totalPage = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);

        return meetingList;
    }
    
    /**
     * 添加会议
     * @param meeting
     * @return
     */
    public int addMeeting(Meeting meeting) {
        String sql = "INSERT INTO t_meeting (title, convene_time, content, status) " +
                "VALUES (?, ?, ?, ?)";
        return jdbc.update(sql, meeting.getTitle(), meeting.getConveneTime()
                , meeting.getContent(), meeting.getStatus());
    }

    /**
     * 修改会议
     * @param meeting
     * @return
     */
    public int modifyMeeting(Meeting meeting) {
        String sql = "UPDATE t_meeting SET title = ?, convene_time = ?, content = ?, status = ? " +
                "WHERE meeting_id = ?";
        return jdbc.update(sql, meeting.getTitle(), meeting.getConveneTime(), meeting.getContent()
                , meeting.getStatus(), meeting.getMeetingId());
    }

    /**
     * 删除会议
     * @param meetingId
     * @return
     */
    public int removeMeeting(Integer meetingId) {
        String sql = "UPDATE t_meeting SET status = 0 WHERE meeting_id = ?";
        return jdbc.update(sql, meetingId);
    }

    /**
     * 根据会议id获取会议信息
     * @param meetingId
     * @return
     */
    public Meeting getMeetingInfoById(Integer meetingId) {
        String sql = "SELECT * FROM t_meeting WHERE meeting_id = ? AND status = 1";
        List<Meeting> meetingList = jdbc.query(sql, meetingMapper, meetingId);
        if (meetingList != null && meetingList.size() > 0) {
            return meetingList.get(0);
        }
        return null;
    }
}
