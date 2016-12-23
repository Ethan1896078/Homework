package team.t9001.saad.service;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.t9001.saad.common.Page;
import team.t9001.saad.dao.ProjectDao;
import team.t9001.saad.model.Project;

import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/17.
 */
@Service
public class ProjectService {
    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private ProjectDao projectDao;

    /**
     * 获取项目列表
     * @param page
     * @return
     */
    public List<Project> getProjectList(Page page){
        List<Project> projectList = projectDao.getProjectList(page);
        logger.info(JSON.toJSONString(projectList));
        return projectList;
    }

    /**
     * 添加项目
     * @param project
     */
    public int addProject(Project project) {
        return projectDao.addProject(project);
    }

    /**
     * 修改项目
     * @param project
     * @return
     */
    public int modifyProject(Project project) {
        return projectDao.modifyProject(project);
    }

    /**
     * 删除项目
     * @param projectId
     * @return
     */
    public int removeProject(Integer projectId) {
        return projectDao.removeProject(projectId);
    }

    /**
     * 根据项目id获取项目信息
     * @param projectId
     * @return
     */
    public Project getProjectInfoById(Integer projectId) {
        return projectDao.getProjectInfoById(projectId);
    }
}
