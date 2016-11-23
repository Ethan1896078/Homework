package team.t9001.saad.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import team.t9001.saad.common.Page;
import team.t9001.saad.common.UrlConstants;
import team.t9001.saad.model.Article;
import team.t9001.saad.model.Tools;
import team.t9001.saad.service.ToolsService;

import com.alibaba.fastjson.JSON;

/**
 * desc:
 * Created by lifeihu on 2016/11/16.
 */
@Controller
@RequestMapping("/tools")
public class ToolsController {
    private static final Logger logger = LoggerFactory.getLogger(ToolsController.class);

    @Autowired
    private ToolsService toolsService;

    /**
     * 添加
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.add_tools, method = RequestMethod.POST)
    public int add(Tools tools){
        logger.info("add tools begin.");
        int result = toolsService.add(tools);
        return result;
    }

    /**
     * 获取列表
     * @param page
     * @return
     */
    @RequestMapping(value = UrlConstants.list_tools, method = RequestMethod.GET)
    public ModelAndView getArticleList(Page page, String toolsType, Integer userId){
        ModelAndView mv = new ModelAndView("tools/list");

        RequestStatus requestStatus = new RequestStatus();
        if (!Validator.validateAdminRights(requestStatus, userId)
                || !Validator.validatePage(requestStatus, page)) {
            mv.addObject(requestStatus);
            return mv;
        }

        List<Tools> list = toolsService.getList(page, toolsType);
        logger.info("get tools list, page:{}, data:{}", JSON.toJSONString(page), JSON.toJSONString(list));


        mv.addObject("list", list);
        mv.addObject("requestStatus", requestStatus);
        return mv;
    }
    
    /**
     * 详情
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.get_tools, method = RequestMethod.POST)
    public ModelAndView get(Integer toolsId){
    	ModelAndView mv = new ModelAndView("tools/detail");
        Tools tools = toolsService.getById(toolsId);
        mv.addObject("tools", tools);
        return mv;
    }

    /**
     * 修改
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.modify_tools, method = RequestMethod.POST)
    public int modify(Tools tools){
        int result = toolsService.modify(tools);
        return result;
    }

    /**
     * 删除
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.remove_tools, method = RequestMethod.POST)
    public int remove(Integer toolsId){
        int result = toolsService.remove(toolsId);
        return result;
    }
}
