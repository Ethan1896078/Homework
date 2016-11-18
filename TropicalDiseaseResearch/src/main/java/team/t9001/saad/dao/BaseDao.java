package team.t9001.saad.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据库连接服务抽象对象，各项目需要继承并初始化jdbc
 * @author jahe
 *
 */
@Component
public abstract class BaseDao {
	
	public Logger log = LoggerFactory.getLogger(getClass());
	
	public JdbcTemplate jdbc;

	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);
	}
	
//	初始化jdbc
//	@Resource(name="dataSource")
//	public void setDataSource(DataSource dataSource) {
//		jdbc = new JdbcTemplate(dataSource);
//	}
	
	public JdbcTemplate jdbc(){
		return jdbc;
	}
	
	/**
	 * 简单查询,PreparedStatement形式
	 * @param <T>
	 * 
	 * @param sql
	 *            查询语句
	 * @param rowMapper
	 * @param values
	 *            查询参数
	 * @return
	 * @throws Exception
	 */
	
	public <T> List<T> query(String sql, final RowMapper<T> rowMapper, Object... values) {
		final List<T> list = new ArrayList<T>();
		jdbc.query(sql, values, new ResultSetExtractor<List<T>>() {
			public List<T> extractData(ResultSet rs) {
				try {
					int i = 0;
					while (rs.next()) {
						list.add(rowMapper.mapRow(rs, i));
						i++;
					}
				} catch (SQLException e) {
					e.printStackTrace();
					log.error("RowMapper-query error", e);
				}
				return list;
			}
		});
		return list;
	}
	
	public <T> T queryT(String sql, final RowMapper<T> rowMapper, Object... values) {
		try {
			return jdbc.queryForObject(sql, rowMapper, values);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	
	public <T> List<T> queryForList(String sql , Class<T> elementType , Object... args){
		try {
			return jdbc.queryForList(sql, elementType, args);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	
	public List<Map<String, Object>> queryList(String sql, Object... args) {
		try {
			return jdbc.queryForList(sql, args);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String queryString(String sql, Object... value) {
		try {
			return jdbc.queryForObject(sql, value, String.class);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	/**
	 * 执行update / insert 语句
	 * 
	 * @param sql
	 *            sql 语句
	 * @param args
	 *            参数数组
	 * @return
	 * @throws Exception
	 */
	
	public boolean update(String sql, Object... args) {
		try {
			int c = jdbc.update(sql, args);
			return c > 0;
		} catch (DataAccessException dae) {
			log.error("update-" + sql, dae);
		}
		return false;
	}
	
	public int update(PreparedStatementCreator psc, KeyHolder key) {
		try {
			int c = jdbc.update(psc, key);
			return c;
		} catch (DataAccessException dae) {
			log.error("update-recharge", dae);
		}
		return 0;
	}
	
	
	/**
	 *  插入数据,返回自增id
	 * @param sql  
	 * @param key 主键字段名
	 * @param args	参数列表
	 * @return -1-异常 
	 */
	
	public long insert(final String sql, final String key , final Object... args ){
		long result = -1L;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc  = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql,new String[] { key });
					if ( args != null) {
						for(int i=0; i<args.length;i++ ){
							ps.setObject(i+1, args[i]);
						}
					}
					return ps;
				}
			};
		result = jdbc.update(psc, keyHolder);
		return result>0?keyHolder.getKey().intValue():-1;
	}
	
	/**
	 *  插入数据,返回新增记录数
	 * @param sql  
	 * @param args	参数列表
	 * @return 新增记录数
	 */
	
	public int insert(final String sql , final Object... args ){
		int result = 0;
		PreparedStatementCreator psc  = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql);
					if ( args != null) {
						for(int i=0; i<args.length;i++ ){
							ps.setObject(i+1, args[i]);
						}
					}
					return ps;
				}
			};
		result = jdbc.update(psc);
		return result;
	}
	
	/**
	 * 批量更新
	 * @author ruan
	 * @param sql sql语句数组
	 * @return 每一条sql语句对应影响的行数
	 */
	public int[] batchUpdate(String[] sql){
		try {
			return jdbc.batchUpdate(sql);
		} catch (DataAccessException e) {
			String str = "";
			for(String s : sql){
				str += s;
			}
			log.error("batchUpdate-" + str, e);
		}
		return null;
	}
	
	
	/**
	 * 批量执行update / insert 语句
	 * @param sql
	 * @return
	 */
	
	public int[] batchUpdate(String sql,List<Object[]> batchArgs) {
		try {
			return jdbc.batchUpdate(sql, batchArgs);
		} catch (DataAccessException dae) {
			log.error("batchUpdate sql:{}, error:{}", sql, dae);
			return null;
		}
	}
	
	/**
	 * 翻页计算
	 * @param page 1-第一页 2-第二页
	 * @param pagesize
	 * @return
	 */
	public int calStart(int page , int pagesize ) {
		if(page<1) {
			page=1;	
		}
		return (page-1)*pagesize;
	}
	
	
	public List<Map<String, Object>> queryForList(String sql, Object... args) {
		return jdbc.queryForList( sql,args);
	}
	
	
	public <T> T queryForObject(String sql, Class<T> requiredType, Object... args) throws DataAccessException {
		return jdbc.queryForObject(sql,requiredType,args);
	}
	
	
	public Map<String,Object> queryForMap(String sql) throws DataAccessException {
		return jdbc.queryForMap(sql);
	}
	
	
	public void execute(String sql) throws DataAccessException {
		jdbc.execute(sql);
	}
	
	
	
	public int[] batchUpdate(String sql, BatchPreparedStatementSetter pss) throws DataAccessException {
		return jdbc.batchUpdate(sql, pss);
	}

}
