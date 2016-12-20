package team.t9001.saad.service;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.t9001.saad.common.Page;
import team.t9001.saad.dao.ArticleDao;
import team.t9001.saad.model.Article;

import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/17.
 */
@Service
public class ArticleService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    private ArticleDao articleDao;

    /**
     * 获取文章列表
     * @param page
     * @return
     */
    public List<Article> getArticleList(Page page){
        List<Article> articleList = articleDao.getArticleList(page);
        logger.info(JSON.toJSONString(articleList));
        return articleList;
    }

    /**
     * 添加文章
     * @param article
     */
    public int addArticle(Article article) {
        return articleDao.addArticle(article);
    }

    /**
     * 修改文章
     * @param article
     * @return
     */
    public int modifyArticle(Article article) {
        return articleDao.modifyArticle(article);
    }

    /**
     * 删除文章
     * @param articleId
     * @return
     */
    public int removeArticle(Integer articleId) {
        return articleDao.removeArticle(articleId);
    }

    /**
     * 根据文章id获取文章信息
     * @param articleId
     * @return
     */
    public Article getArticleInfoById(Integer articleId) {
        return articleDao.getArticleInfoById(articleId);
    }
}
