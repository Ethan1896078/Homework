package team.t9001.saad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import team.t9001.saad.common.Page;
import team.t9001.saad.model.Tools;

/**
 * desc:
 * Created by lifeihu on 2016/11/17.
 */
@Repository
public class ToolsDao extends BaseDao {
	
    private RowMapper<Tools> toolsMapper = new RowMapper<Tools>() {

        public Tools mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Tools tools = new Tools();
        	tools.setToolsId(rs.getInt("tools_id"));
        	tools.setToolsType(rs.getString("tools_type"));
        	tools.setToolsTitle(rs.getString("tools_title"));
        	tools.setToolsUrl(rs.getString("tools_url"));
        	tools.setCreateDate(rs.getDate("create_date"));
            return tools;
        }
    };

    /**
     * 获取列表
     * @return
     */
    public List<Tools> getList(Page page, String toolsType) {
        int pageSize = page.getPageSize();
        int beginIndex = page.getCurrentPage() * pageSize;

        String sql = "SELECT tools_id, tools_title, create_date FROM t_tools where tools_type = ? LIMIT ?, ?";
        List<Tools> list = jdbc.query(sql, toolsMapper, toolsType, beginIndex, pageSize);

        String countSql = "SELECT COUNT(0) FROM t_tools  where tools_type = ?";
        int totalCount = jdbc.queryForObject(countSql, Integer.class, toolsType);
        int totalPage = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);

        return list;
    }
    
    public Tools getById(Integer toolsId){
    	String sql = "SELECT * FROM t_tools WHERE tools_id = ?";
    	Tools tools = jdbc.queryForObject(sql, toolsMapper, toolsId);
        return tools;
    }

    /**
     * 添加
     * @param 
     * @return
     */
    public int add(Tools tools) {
        String sql = "INSERT INTO t_tools (tools_type, tools_title, tools_url, create_date) " +
                "VALUES (?, ?, ?, now())";
        return jdbc.update(sql, tools.getToolsType(), tools.getToolsTitle(), tools.getToolsUrl());
    }

    /**
     * 修改
     * @param tools
     * @return
     */
    public int modify(Tools tools) {
        String sql = "UPDATE t_tools SET tools_title = ?, tools_url = ?, create_date = now() " +
                "WHERE tools_id = ?";
        return jdbc.update(sql, tools.getToolsTitle(), tools.getToolsUrl(), tools.getToolsId());
    }

    /**
     * 删除
     * @param 
     * @return
     */
    public int remove(Integer toolsId) {
        String sql = "DELETE FROM t_tools WHERE tools_id = ?";
        return jdbc.update(sql, toolsId);
    }
}
