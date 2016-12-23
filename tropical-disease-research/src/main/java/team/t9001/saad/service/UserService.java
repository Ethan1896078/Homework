package team.t9001.saad.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import team.t9001.saad.common.Page;
import team.t9001.saad.dao.UserDao;
import team.t9001.saad.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/22.
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDao userDao;

    /**
     * 通过用户编号获取用户信息
     * @param userId
     * @return
     */
    public User getUserInfoById(int userId){
        return userDao.getUserInfoById(userId);
    }

    /**
     * 通过登录账号获取用户信息
     * @param account
     * @return
     */
    public User getUserInfoByAccount(String account){
        return userDao.getUserInfoByAccount(account);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    public int addUser(User user) {
//        calcPassword(user);
        return userDao.addUser(user);
    }

    /**
     * 获取用户列表
     * @param page
     * @return
     */
    public List<User> getUserList(Page page){
        List<User> userList = userDao.getUserList(page);
        return userList;
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    public int modifyUser(User user) {
        calcPassword(user);
        return userDao.modifyUser(user);
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    public int removeUser(Integer userId) {
        return userDao.removeUser(userId);
    }

    /**
     * 从session中获取登录用户
     * @param request
     * @return
     */
    public Integer getLoginUserId(HttpServletRequest request){
        String userId;
        Object userIdObject = request.getSession().getAttribute("userId");
        if (userIdObject != null) {
            userId = userIdObject.toString();
        } else {
            userId = request.getParameter("loginUserId");
        }
        logger.debug("login user id:{}", userId);
        if (StringUtils.isBlank(userId)){
            return null;
        }
        return Integer.valueOf(userId);
    }

    /**
     * 计算加密后的密码
     * @param user
     */
    private void calcPassword(User user){
        String account = user.getAccount();
        String passwordPlain = user.getPassword();
        String passwordEncrypt = DigestUtils.md5DigestAsHex((account + passwordPlain).getBytes());
        user.setPassword(passwordEncrypt);
    }
}
