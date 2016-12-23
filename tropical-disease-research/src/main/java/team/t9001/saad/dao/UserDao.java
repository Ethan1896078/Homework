package team.t9001.saad.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import team.t9001.saad.common.Page;
import team.t9001.saad.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/22.
 */
@Repository
public class UserDao extends BaseDao {
    private RowMapper<User> userMapper = new RowMapper<User>() {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setType(rs.getInt("type"));
            user.setStatus(rs.getInt("status"));
            user.setAccount(rs.getString("account"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    };

    /**
     * 添加用户
     * @param user
     * @return
     */
    public int addUser(User user){
        String sql = "INSERT INTO t_user_1 (username, type, status, account, password) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbc.update(sql, user.getUsername(), user.getType(), user.getStatus(), user.getAccount(), user.getPassword());
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    public int modifyUser(User user){
        String sql = "UPDATE t_user_1 SET type = ?, status = ? " +
                "WHERE user_id = ?";
        return jdbc.update(sql, user.getType(), user.getStatus(), user.getUserId());
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    public int removeUser(int userId) {
        String sql = "UPDATE t_user_1 SET status = 0 WHERE user_id = ?";
        return jdbc.update(sql, userId);
    }

    /**
     * 通过用户编号获取用户详细信息
     * @param userId
     * @return
     */
    public User getUserInfoById(int userId) {
        String sql = "SELECT * FROM t_user_1 WHERE user_id = ? AND status = 1";
        List<User> userList = jdbc.query(sql, userMapper, userId);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    /**
     * 通过登录账号获取用户详细信息
     * @param account
     * @return
     */
    public User getUserInfoByAccount(String account) {
        String sql = "SELECT * FROM t_user_1 WHERE account = ? AND status = 1";
        List<User> userList = jdbc.query(sql, userMapper, account);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    /**
     * 获取用户列表
     * @param page
     * @return
     */
    public List<User> getUserList(Page page) {
        int pageSize = page.getPageSize();
        int beginIndex = page.getCurrentPage() * pageSize;

        String sql = "SELECT * FROM t_user_1 WHERE status = 1 AND status = 1 LIMIT ?, ?";
        List<User> userList = jdbc.query(sql, userMapper, beginIndex, pageSize);

        String countSql = "SELECT COUNT(0) FROM t_user_1";
        int totalCount = jdbc.queryForObject(countSql, Integer.class);
        int totalPage = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);

        return userList;
    }

}
