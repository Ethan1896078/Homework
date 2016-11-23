package team.t9001.saad.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.t9001.saad.common.Page;
import team.t9001.saad.dao.AnalysisDao;
import team.t9001.saad.dao.ArticleDao;
import team.t9001.saad.model.Analysis;
import team.t9001.saad.model.Article;

import com.alibaba.fastjson.JSON;

/**
 * desc:
 * Created by lifeihu on 2016/11/17.
 */
@Service
public class AnalysisService {
    private static final Logger logger = LoggerFactory.getLogger(AnalysisService.class);

    @Autowired
    private AnalysisDao analysisDao;
    
    @Autowired
    private ArticleDao articleDao;

    /**
     * 获取列表
     * @param analysisType分析类型，author作者；magazine期刊；year年份
     * @return
     */
    public List<Analysis> getAnalysisList(String analysisType){
        List<Analysis> list = analysisDao.getAnalysisList(analysisType);
        logger.info(JSON.toJSONString(list));
        return list;
    }

    /**
     * 获取统计文章列表
     * @return
     */
    public List<Article> getAnalysisArticleList(Page page, String analysisType, String analysisValue){
    	return articleDao.getAnalysisArticleList(page, analysisType, analysisValue);
    }
    
}
