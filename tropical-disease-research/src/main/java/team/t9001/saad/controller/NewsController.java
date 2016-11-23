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
import team.t9001.saad.model.News;
import team.t9001.saad.service.NewsService;

import com.alibaba.fastjson.JSON;

/**
 * desc:
 * Created by lifeihu on 2016/11/16.
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

    /**
     * 添加
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.add_news, method = RequestMethod.POST)
    public int add(News news){
        logger.info("add news begin.");
        int result = newsService.add(news);
        return result;
    }

    /**
     * 获取列表
     * @param page
     * @return
     */
    @RequestMapping(value = UrlConstants.list_news, method = RequestMethod.GET)
    public ModelAndView getArticleList(Page page, Integer userId){
        ModelAndView mv = new ModelAndView("news/list");

        RequestStatus requestStatus = new RequestStatus();
        if (!Validator.validateAdminRights(requestStatus, userId)
                || !Validator.validatePage(requestStatus, page)) {
            mv.addObject(requestStatus);
            return mv;
        }

        List<News> list = newsService.getList(page);
        logger.info("get news list, page:{}, data:{}", JSON.toJSONString(page), JSON.toJSONString(list));


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
    @RequestMapping(value = UrlConstants.get_news, method = RequestMethod.POST)
    public ModelAndView get(Integer newsId){
    	ModelAndView mv = new ModelAndView("news/detail");
        News news = newsService.getById(newsId);
        mv.addObject("news", news);
        return mv;
    }

    /**
     * 修改
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.modify_news, method = RequestMethod.POST)
    public int modify(News news){
        int result = newsService.modify(news);
        return result;
    }

    /**
     * 删除
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.remove_news, method = RequestMethod.POST)
    public int remove(Integer newsId){
        int result = newsService.remove(newsId);
        return result;
    }
}
