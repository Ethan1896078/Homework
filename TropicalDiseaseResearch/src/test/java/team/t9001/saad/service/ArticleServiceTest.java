package team.t9001.saad.service;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import team.t9001.saad.model.Article;

import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/15.
 */
@ContextConfiguration(locations = "file:src/main/resources/application.xml")
public class ArticleServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceTest.class);

    @Autowired
    private ArticleService articleService;

    @Test
    public void testGetArticleList(){
        List<Article> articleList = articleService.getArticleList();
        logger.info("get article list: {}.", JSON.toJSONString(articleList));
    }

    @Test
    @Rollback(false)
    public void testAddArticle(){
        Article article = new Article();
        article.setTitle("买买买");
        article.setAuthor("马云");
        article.setPublishTime("2005-12-12");
        article.setExternalLink("www.taobao.com");
        article.setStatus(0);
        int result = articleService.addArticle(article);
        logger.info("add article, result: {}", JSON.toJSONString(result));
    }
}
