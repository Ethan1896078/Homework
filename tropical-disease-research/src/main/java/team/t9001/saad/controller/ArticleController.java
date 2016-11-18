package team.t9001.saad.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import team.t9001.saad.common.UrlConstants;
import team.t9001.saad.model.Article;
import team.t9001.saad.service.ArticleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.add_article, method = RequestMethod.POST)
    public int addArticle(HttpServletRequest request, HttpServletResponse response, Article article){
        logger.info("add article begin.");
        int result = articleService.addArticle(article);
        return result;
    }

    /**
     * 获取文章列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = UrlConstants.get_article_list, method = RequestMethod.GET)
    public ModelAndView getArticleList(HttpServletRequest request, HttpServletResponse response){
        logger.info("get article list begin.");
        List<Article> articleList = articleService.getArticleList();

        ModelAndView mv = new ModelAndView("article/list");
        mv.addObject("articleList", articleList);
        return mv;
    }

    /**
     * 修改文章
     * @param request
     * @param response
     * @param article
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.modify_article, method = RequestMethod.POST)
    public int modifyArticle(HttpServletRequest request, HttpServletResponse response, Article article){
        int result = articleService.modifyArticle(article);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = UrlConstants.remove_article, method = RequestMethod.POST)
    public int removeArticle(HttpServletRequest request, HttpServletResponse response, Integer articleId){
        int result = articleService.removeArticle(articleId);
        return result;
    }
}
