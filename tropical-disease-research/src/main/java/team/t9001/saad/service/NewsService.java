package team.t9001.saad.service;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.t9001.saad.common.Page;
import team.t9001.saad.dao.NewsDao;
import team.t9001.saad.model.News;
import team.t9001.saad.model.News;

import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/17.
 */
@Service
public class NewsService {
    private static final Logger logger = LoggerFactory.getLogger(NewsService.class);

    @Autowired
    private NewsDao newsDao;

    /**
     * 获取新闻列表
     * @param page
     * @return
     */
    public List<News> getNewsList(Page page){
        List<News> newsList = newsDao.getNewsList(page);
        logger.info(JSON.toJSONString(newsList));
        return newsList;
    }

    /**
     * 添加新闻
     * @param news
     */
    public int addNews(News news) {
        return newsDao.addNews(news);
    }

    /**
     * 修改新闻
     * @param news
     * @return
     */
    public int modifyNews(News news) {
        return newsDao.modifyNews(news);
    }

    /**
     * 删除新闻
     * @param newsId
     * @return
     */
    public int removeNews(Integer newsId) {
        return newsDao.removeNews(newsId);
    }

    /**
     * 根据新闻id获取新闻信息
     * @param newsId
     * @return
     */
    public News getNewsInfoById(Integer newsId) {
        return newsDao.getNewsInfoById(newsId);
    }
}
