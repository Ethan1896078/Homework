package team.t9001.saad.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.t9001.saad.common.*;
import team.t9001.saad.model.Team;
import team.t9001.saad.service.TeamService;
import team.t9001.saad.service.ValidatorService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/16.
 */
@Controller
@RequestMapping("/team")
public class TeamController {
    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamService teamService;
    @Autowired
    private ValidatorService validatorService;

    /**
     * 添加团队
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.add_team, method = RequestMethod.POST)
    public RequestStatus addTeam(HttpServletRequest request, Team team){
        logger.debug("add team begin, team: {}", team);
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (team == null
                || team.getTeamName() == null
                || team.getLeader() == null
                || team.getStatus() < 0) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，team = " + team);
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = teamService.addTeam(team);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("add team success, team:{}.", team);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }

    /**
     * 获取团队列表
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.get_team_list, method = RequestMethod.GET)
    public RequestStatus getTeamList(HttpServletRequest request, Page page){
        RequestStatus requestStatus = new RequestStatus();
        if (!validatorService.validateLogin(requestStatus, request)) {
            return requestStatus;
        }

        if (!validatorService.validatePage(requestStatus, page)) {
            return requestStatus;
        }

        List<Team> teamList = teamService.getTeamList(page);
        logger.info("get team list, page:{}, data:{}", page, teamList);

        requestStatus.setData(JSON.toJSONString(teamList));
        return requestStatus;
    }

    /**
     * 获取团队信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.get_team_info, method = RequestMethod.GET)
    public RequestStatus getTeamInfo(HttpServletRequest request, Integer teamId){
        RequestStatus requestStatus = new RequestStatus();
        //校验参数
        if (teamId == null) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，teamId = " + teamId);
            return requestStatus;
        }

        if (!validatorService.validateLogin(requestStatus, request)) {
            return requestStatus;
        }

        Team teamInfo = teamService.getTeamInfoById(teamId);
        logger.info("get team info, data:{}", teamInfo);

        requestStatus.setData(JSON.toJSONString(teamInfo));
        return requestStatus;
    }

    /**
     * 修改团队
     * @param team
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.modify_team, method = RequestMethod.POST)
    public RequestStatus modifyTeam(HttpServletRequest request, Team team){
        logger.debug("modify team begin.");
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (team == null
                || team.getTeamId() <= 0
                || team.getTeamName() == null
                || team.getLeader() == null
                || team.getStatus() < 0) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，team = " + team);
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = teamService.modifyTeam(team);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("modify team success, team:{}.", team);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }

    /**
     * 删除团队
     * @param teamId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.remove_team, method = RequestMethod.POST)
    public RequestStatus removeTeam(HttpServletRequest request, Integer teamId){
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (teamId == null) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，teamId = null");
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = teamService.removeTeam(teamId);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("remove team success, teamId:{}.", teamId);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }
}
