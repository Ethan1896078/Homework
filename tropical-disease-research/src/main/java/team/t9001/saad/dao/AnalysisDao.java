package team.t9001.saad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import team.t9001.saad.model.Analysis;

/**
 * desc:
 * Created by lifeihu on 2016/11/17.
 */
@Repository
public class AnalysisDao extends BaseDao {
	
    private RowMapper<Analysis> newsMapper = new RowMapper<Analysis>() {

        public Analysis mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Analysis analysis = new Analysis();
        	analysis.setTitle(rs.getString("a_title"));
        	analysis.setCount(rs.getInt("a_count"));
            return analysis;
        }
    };

    /**
     * 获取列表
     * @param analysisType分析类型，author作者；magazine期刊；year年份
     * @return
     */
    public List<Analysis> getAnalysisList(String analysisType) {
    	String sql = null;
    	if("author".equals(analysisType)){
    		sql = "select author a_title, count(article_id) a_count from t_article GROUP BY a_title order by a_count desc";
    	}else if("magazine".equals(analysisType)){
    		sql = "select magazine a_title, count(article_id) a_count from t_article GROUP BY a_title order by a_count desc";
    	}else if("year".equals(analysisType)){
    		sql = "select left(publish_time, 4) a_title, count(article_id) a_count from t_article GROUP BY a_title order by a_count desc";
    	}else {
    		return new ArrayList<Analysis>();
    	}
        List<Analysis> list = jdbc.query(sql, newsMapper);
        return list;
    }
    
}
