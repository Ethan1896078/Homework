package team.t9001.saad.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.t9001.saad.common.Page;
import team.t9001.saad.dao.NewsDao;
import team.t9001.saad.model.News;

import com.alibaba.fastjson.JSON;

/**
 * desc:
 * Created by lifeihu on 2016/11/17.
 */
@Service
public class NewsService {
    private static final Logger logger = LoggerFactory.getLogger(NewsService.class);

    @Autowired
    private NewsDao newsDao;

    /**
     * 获取列表
     * @return
     */
    public List<News> getList(Page page){
        List<News> list = newsDao.getList(page);
        logger.info(JSON.toJSONString(list));
        return list;
    }

    public News getById(Integer newsId){
    	return newsDao.getById(newsId);
    }
    
    /**
     * 添加
     * @param 
     */
    public int add(News news) {
        return newsDao.add(news);
    }

    /**
     * 修改
     * @param 
     * @return
     */
    public int modify(News news) {
        return newsDao.modify(news);
    }

    /**
     * 删除
     * @param 
     * @return
     */
    public int remove(Integer newsId) {
        return newsDao.remove(newsId);
    }
}
