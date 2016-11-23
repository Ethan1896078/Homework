package team.t9001.saad.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import team.t9001.saad.common.Page;
import team.t9001.saad.common.UrlConstants;
import team.t9001.saad.model.Analysis;
import team.t9001.saad.model.Article;
import team.t9001.saad.service.AnalysisService;

import com.alibaba.fastjson.JSON;

/**
 * desc:
 * Created by lifeihu on 2016/11/16.
 */
@Controller
@RequestMapping("/analysis")
public class AnalysisController {
    private static final Logger logger = LoggerFactory.getLogger(AnalysisController.class);

    @Autowired
    private AnalysisService analysisService;

    /**
     * 获取统计列表
     * @param page
     * @return
     */
    @RequestMapping(value = UrlConstants.list_analysis, method = RequestMethod.GET)
    public ModelAndView getAnalysisList(Page page, String analysisType, Integer userId){
        ModelAndView mv = new ModelAndView("analysis/list");

        List<Analysis> list = analysisService.getAnalysisList(analysisType);
        logger.info("get analysis list, page:{}, data:{}", JSON.toJSONString(page), JSON.toJSONString(list));


        mv.addObject("list", list);
        return mv;
    }
    
    /**
     * 获取统计列表
     * @param page
     * @return
     */
    @RequestMapping(value = UrlConstants.list_analysis_article, method = RequestMethod.GET)
    public ModelAndView getAnalysisArticleList(Page page, String analysisType, String analysisValue, Integer userId){
        ModelAndView mv = new ModelAndView("analysis/list");

        List<Article> list = analysisService.getAnalysisArticleList(page, analysisType, analysisValue);
        logger.info("get analysis list, page:{}, data:{}", JSON.toJSONString(page), JSON.toJSONString(list));


        mv.addObject("list", list);
        return mv;
    }
}
