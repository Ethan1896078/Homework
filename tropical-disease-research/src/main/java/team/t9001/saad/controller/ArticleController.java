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
import team.t9001.saad.model.Article;
import team.t9001.saad.service.ArticleService;
import team.t9001.saad.service.ValidatorService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/16.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ValidatorService validatorService;

    /**
     * 添加文章
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.add_article, method = RequestMethod.POST)
    public RequestStatus addArticle(HttpServletRequest request, Article article){
        logger.debug("add article begin, article: {}", article);
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (article == null
                || article.getTitle() == null
                || article.getExternalLink() == null
                || article.getStatus() < 0) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，article = " + article);
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = articleService.addArticle(article);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("add article success, article:{}.", article);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }

    /**
     * 获取文章列表
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.get_article_list, method = RequestMethod.GET)
    public RequestStatus getArticleList(HttpServletRequest request, Page page){
        RequestStatus requestStatus = new RequestStatus();
        if (!validatorService.validateLogin(requestStatus, request)) {
            return requestStatus;
        }

        if (!validatorService.validatePage(requestStatus, page)) {
            return requestStatus;
        }

        List<Article> articleList = articleService.getArticleList(page);
        logger.info("get article list, page:{}, data:{}", page, articleList);

        requestStatus.setData(JSON.toJSONString(articleList));
        return requestStatus;
    }

    /**
     * 修改文章
     * @param article
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.modify_article, method = RequestMethod.POST)
    public RequestStatus modifyArticle(HttpServletRequest request, Article article){
        logger.debug("modify article begin.");
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (article == null
                || article.getArticleId() <= 0
                || article.getTitle() == null
                || article.getExternalLink() == null
                || article.getStatus() < 0) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，article = " + article);
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = articleService.modifyArticle(article);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("modify article success, article:{}.", article);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }

    /**
     * 删除文章
     * @param articleId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.remove_article, method = RequestMethod.POST)
    public RequestStatus removeArticle(HttpServletRequest request, Integer articleId){
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (articleId == null) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，articleId = null");
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = articleService.removeArticle(articleId);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("remove article success, articleId:{}.", articleId);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }
}
