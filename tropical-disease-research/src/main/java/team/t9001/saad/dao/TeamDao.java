package team.t9001.saad.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import team.t9001.saad.common.Page;
import team.t9001.saad.model.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/17.
 */
@Repository
public class TeamDao extends BaseDao {
    private RowMapper<Team> teamMapper = new RowMapper<Team>() {

        public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
            Team team = new Team();
            team.setTeamId(rs.getInt("team_id"));
            team.setTeamName(rs.getString("team_name"));
            team.setLeader(rs.getString("leader"));
            team.setMembers(rs.getString("members"));
            team.setPartTeam(rs.getString("part_team"));
            team.setStatus(rs.getInt("status"));
            return team;
        }
    };

    /**
     * 获取团队列表
     * @return
     */
    public List<Team> getTeamList(Page page) {
        int pageSize = page.getPageSize();
        int beginIndex = page.getCurrentPage() * pageSize;

        String sql = "SELECT * FROM t_team WHERE status = 1 LIMIT ?, ?";
        List<Team> teamList = jdbc.query(sql, teamMapper, beginIndex, pageSize);

        String countSql = "SELECT COUNT(0) FROM t_team";
        int totalCount = jdbc.queryForObject(countSql, Integer.class);
        int totalPage = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);

        return teamList;
    }

    /**
     * 添加团队
     * @param team
     * @return
     */
    public int addTeam(Team team) {
        String sql = "INSERT INTO t_team (team_name, leader, members, part_team, status) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbc.update(sql, team.getTeamName(), team.getLeader(), team.getMembers()
                , team.getPartTeam(), team.getStatus());
    }

    /**
     * 修改团队
     * @param team
     * @return
     */
    public int modifyTeam(Team team) {
        String sql = "UPDATE t_team SET team_name = ?, leader = ?, members = ?, part_team = ?, status = ? " +
                "WHERE team_id = ?";
        return jdbc.update(sql, team.getTeamName(), team.getLeader(), team.getMembers()
                , team.getPartTeam(), team.getStatus(), team.getTeamId());
    }

    /**
     * 删除团队
     * @param teamId
     * @return
     */
    public int removeTeam(Integer teamId) {
        String sql = "UPDATE t_team SET status = 0 WHERE team_id = ?";
        return jdbc.update(sql, teamId);
    }

    /**
     * 根据团队id获取团队信息
     * @param teamId
     * @return
     */
    public Team getTeamInfoById(Integer teamId) {
        String sql = "SELECT * FROM t_team WHERE team_id = ? AND status = 1";
        List<Team> teamList = jdbc.query(sql, teamMapper, teamId);
        if (teamList != null && teamList.size() > 0) {
            return teamList.get(0);
        }
        return null;
    }
}
