package team.t9001.saad.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.t9001.saad.common.Page;
import team.t9001.saad.dao.ToolsDao;
import team.t9001.saad.model.Tools;

import com.alibaba.fastjson.JSON;

/**
 * desc:
 * Created by lifeihu on 2016/11/17.
 */
@Service
public class ToolsService {
    private static final Logger logger = LoggerFactory.getLogger(ToolsService.class);

    @Autowired
    private ToolsDao toolsDao;

    /**
     * 获取列表
     * @return
     */
    public List<Tools> getList(Page page, String toolsType){
        List<Tools> list = toolsDao.getList(page, toolsType);
        logger.info(JSON.toJSONString(list));
        return list;
    }

    public Tools getById(Integer toolsId){
    	return toolsDao.getById(toolsId);
    }
    
    /**
     * 添加
     * @param 
     */
    public int add(Tools tools) {
        return toolsDao.add(tools);
    }

    /**
     * 修改
     * @param 
     * @return
     */
    public int modify(Tools tools) {
        return toolsDao.modify(tools);
    }

    /**
     * 删除
     * @param 
     * @return
     */
    public int remove(Integer toolsId) {
        return toolsDao.remove(toolsId);
    }
}
