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
import team.t9001.saad.model.Project;
import team.t9001.saad.service.ProjectService;
import team.t9001.saad.service.ValidatorService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/16.
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ValidatorService validatorService;

    /**
     * 添加项目
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.add_project, method = RequestMethod.POST)
    public RequestStatus addProject(HttpServletRequest request, Project project){
        logger.debug("add project begin, project: {}", project);
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (project == null
                || project.getProjectName() == null
                || project.getLeader() == null
                || project.getContent() == null
                || project.getStatus() < 0) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，project = " + project);
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = projectService.addProject(project);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("add project success, project:{}.", project);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }

    /**
     * 获取项目信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.get_project_info, method = RequestMethod.GET)
    public RequestStatus getProjectInfo(HttpServletRequest request, Integer projectId){
        RequestStatus requestStatus = new RequestStatus();
        //校验参数
        if (projectId == null) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，projectId = " + projectId);
            return requestStatus;
        }

        if (!validatorService.validateLogin(requestStatus, request)) {
            return requestStatus;
        }

        Project projectInfo = projectService.getProjectInfoById(projectId);
        logger.info("get project info, data:{}", projectInfo);

        requestStatus.setData(JSON.toJSONString(projectInfo));
        return requestStatus;
    }

    /**
     * 获取项目列表
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.get_project_list, method = RequestMethod.GET)
    public RequestStatus getProjectList(HttpServletRequest request, Page page){
        logger.debug("get project list, page:{}", page);
        RequestStatus requestStatus = new RequestStatus();
        if (!validatorService.validateLogin(requestStatus, request)) {
            return requestStatus;
        }

        if (!validatorService.validatePage(requestStatus, page)) {
            return requestStatus;
        }

        List<Project> projectList = projectService.getProjectList(page);
        logger.info("get project list, page:{}, data:{}", page, projectList);

        requestStatus.setData(JSON.toJSONString(projectList));
        return requestStatus;
    }

    /**
     * 修改项目
     * @param project
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.modify_project, method = RequestMethod.POST)
    public RequestStatus modifyProject(HttpServletRequest request, Project project){
        logger.debug("modify project begin.");
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (project == null
                || project.getProjectName() == null
                || project.getLeader() == null
                || project.getContent() == null
                || project.getStatus() < 0) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，project = " + project);
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = projectService.modifyProject(project);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("modify project success, project:{}.", project);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }

    /**
     * 删除项目
     * @param projectId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.remove_project, method = RequestMethod.POST)
    public RequestStatus removeProject(HttpServletRequest request, Integer projectId){
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (projectId == null) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，projectId = null");
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = projectService.removeProject(projectId);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("remove project success, projectId:{}.", projectId);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }
}
