package team.t9001.saad.service;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.t9001.saad.common.Page;
import team.t9001.saad.dao.TeamDao;
import team.t9001.saad.model.Team;
import team.t9001.saad.model.Team;

import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/17.
 */
@Service
public class TeamService {
    private static final Logger logger = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    private TeamDao teamDao;

    /**
     * 获取团队列表
     * @param page
     * @return
     */
    public List<Team> getTeamList(Page page){
        List<Team> teamList = teamDao.getTeamList(page);
        logger.info(JSON.toJSONString(teamList));
        return teamList;
    }

    /**
     * 添加团队
     * @param team
     */
    public int addTeam(Team team) {
        return teamDao.addTeam(team);
    }

    /**
     * 修改团队
     * @param team
     * @return
     */
    public int modifyTeam(Team team) {
        return teamDao.modifyTeam(team);
    }

    /**
     * 删除团队
     * @param teamId
     * @return
     */
    public int removeTeam(Integer teamId) {
        return teamDao.removeTeam(teamId);
    }

    /**
     * 根据团队id获取团队信息
     * @param teamId
     * @return
     */
    public Team getTeamInfoById(Integer teamId) {
        return teamDao.getTeamInfoById(teamId);
    }
}
