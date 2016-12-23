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
import team.t9001.saad.model.News;
import team.t9001.saad.model.News;
import team.t9001.saad.service.NewsService;
import team.t9001.saad.service.ValidatorService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/16.
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;
    @Autowired
    private ValidatorService validatorService;

    /**
     * 添加新闻
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.add_news, method = RequestMethod.POST)
    public RequestStatus addNews(HttpServletRequest request, News news){
        logger.debug("add news begin, news: {}", news);
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (news == null
                || news.getTitle() == null
                || news.getContent() == null
                || news.getStatus() < 0) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，news = " + news);
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = newsService.addNews(news);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("add news success, news:{}.", news);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }

    /**
     * 获取新闻列表
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.get_news_list, method = RequestMethod.GET)
    public RequestStatus getNewsList(HttpServletRequest request, Page page){
        RequestStatus requestStatus = new RequestStatus();
        if (!validatorService.validateLogin(requestStatus, request)) {
            return requestStatus;
        }

        if (!validatorService.validatePage(requestStatus, page)) {
            return requestStatus;
        }

        List<News> newsList = newsService.getNewsList(page);
        logger.info("get news list, page:{}, data:{}", page, newsList);

        requestStatus.setData(JSON.toJSONString(newsList));
        return requestStatus;
    }

    /**
     * 获取文章信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.get_news_info, method = RequestMethod.GET)
    public RequestStatus getNewsInfo(HttpServletRequest request, Integer newsId){
        RequestStatus requestStatus = new RequestStatus();
        //校验参数
        if (newsId == null) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，newsId = " + newsId);
            return requestStatus;
        }

        if (!validatorService.validateLogin(requestStatus, request)) {
            return requestStatus;
        }

        News newsInfo = newsService.getNewsInfoById(newsId);
        logger.info("get news info, data:{}", newsInfo);

        requestStatus.setData(JSON.toJSONString(newsInfo));
        return requestStatus;
    }

    /**
     * 修改新闻
     * @param news
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.modify_news, method = RequestMethod.POST)
    public RequestStatus modifyNews(HttpServletRequest request, News news){
        logger.debug("modify news begin.");
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (news == null
                || news.getNewsId() <= 0
                || news.getTitle() == null
                || news.getContent() == null
                || news.getStatus() < 0) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，news = " + news);
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = newsService.modifyNews(news);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("modify news success, news:{}.", news);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }

    /**
     * 删除新闻
     * @param newsId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.remove_news, method = RequestMethod.POST)
    public RequestStatus removeNews(HttpServletRequest request, Integer newsId){
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (newsId == null) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，newsId = null");
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = newsService.removeNews(newsId);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("remove news success, newsId:{}.", newsId);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }
}
