package team.t9001.saad.service;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
     * @return
     */
    public List<Article> getArticleList(){
        List<Article> articleList = articleDao.getArticleList();
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

    public int modifyArticle(Article article) {
        return articleDao.modifyArticle(article);
    }

    public int removeArticle(Integer articleId) {
        return 1;
    }
}
