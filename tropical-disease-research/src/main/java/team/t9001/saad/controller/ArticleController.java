package team.t9001.saad.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import team.t9001.saad.common.*;
import team.t9001.saad.model.Article;
import team.t9001.saad.service.ArticleService;

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

    /**
     * 添加文章
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.add_article, method = RequestMethod.POST)
    public int addArticle(Article article){
        logger.info("add article begin.");
        int result = articleService.addArticle(article);
        return result;
    }

    /**
     * 获取文章列表
     * @param page
     * @return
     */
    @RequestMapping(value = UrlConstants.get_article_list, method = RequestMethod.GET)
    public ModelAndView getArticleList(Page page, Integer userId){
        ModelAndView mv = new ModelAndView("article/list");

        RequestStatus requestStatus = new RequestStatus();
        if (!Validator.validateAdminRights(requestStatus, userId)
                || !Validator.validatePage(requestStatus, page)) {
            mv.addObject(requestStatus);
            return mv;
        }

        List<Article> articleList = articleService.getArticleList(page);
        logger.info("get article list, page:{}, data:{}", JSON.toJSONString(page), JSON.toJSONString(articleList));


        mv.addObject("list", articleList);
        mv.addObject("requestStatus", requestStatus);
        return mv;
    }

    /**
     * 修改文章
     * @param article
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.modify_article, method = RequestMethod.POST)
    public int modifyArticle(Article article){
        int result = articleService.modifyArticle(article);
        return result;
    }

    /**
     * 删除文章
     * @param articleId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.remove_article, method = RequestMethod.POST)
    public int removeArticle(Integer articleId){
        int result = articleService.removeArticle(articleId);
        return result;
    }
}
