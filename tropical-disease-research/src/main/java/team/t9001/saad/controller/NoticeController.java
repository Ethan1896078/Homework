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
import team.t9001.saad.model.Notice;
import team.t9001.saad.service.NoticeService;

import com.alibaba.fastjson.JSON;

/**
 * desc:
 * Created by lifeihu on 2016/11/16.
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;

    /**
     * 添加
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.add_notice, method = RequestMethod.POST)
    public int add(Notice notice){
        logger.info("add notice begin.");
        int result = noticeService.add(notice);
        return result;
    }

    /**
     * 获取列表
     * @param page
     * @return
     */
    @RequestMapping(value = UrlConstants.list_notice, method = RequestMethod.GET)
    public ModelAndView getArticleList(Page page, Integer userId){
        ModelAndView mv = new ModelAndView("notice/list");

        RequestStatus requestStatus = new RequestStatus();
        if (!Validator.validateAdminRights(requestStatus, userId)
                || !Validator.validatePage(requestStatus, page)) {
            mv.addObject(requestStatus);
            return mv;
        }

        List<Notice> list = noticeService.getList(page);
        logger.info("get notice list, page:{}, data:{}", JSON.toJSONString(page), JSON.toJSONString(list));


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
    @RequestMapping(value = UrlConstants.get_notice, method = RequestMethod.POST)
    public ModelAndView get(Integer noticeId){
    	ModelAndView mv = new ModelAndView("notice/detail");
        Notice notice = noticeService.getById(noticeId);
        mv.addObject("notice", notice);
        return mv;
    }

    /**
     * 修改
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.modify_notice, method = RequestMethod.POST)
    public int modify(Notice notice){
        int result = noticeService.modify(notice);
        return result;
    }

    /**
     * 删除
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.remove_notice, method = RequestMethod.POST)
    public int remove(Integer noticeId){
        int result = noticeService.remove(noticeId);
        return result;
    }
}
