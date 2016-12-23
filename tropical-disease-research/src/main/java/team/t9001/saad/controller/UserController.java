package team.t9001.saad.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.t9001.saad.common.*;
import team.t9001.saad.model.User;
import team.t9001.saad.service.UserService;
import team.t9001.saad.service.ValidatorService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/23.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ValidatorService validatorService;

    @Autowired
    private UserService userService;

    /**
     * 添加用户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.add_user, method = RequestMethod.POST)
    public RequestStatus register(HttpServletRequest request, User user){
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (user == null
                || StringUtils.isBlank(user.getUsername())
                || user.getType() < 0
                || user.getStatus() < 0
                || StringUtils.isBlank(user.getAccount())
                || StringUtils.isBlank(user.getPassword())) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，user = " + user);
            return requestStatus;
        }

        //校验权限
        if (user.getType() == Constants.USER_TYPE_LAB_WORKER
            && !validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = userService.addUser(user);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("add user success, user:{}.", user);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }

    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.login, method = RequestMethod.POST)
    public RequestStatus login(HttpServletRequest request, String account, String password){
        logger.debug("login, account:{}, password:{}", account, password);
        RequestStatus requestStatus = new RequestStatus();

        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR)
                    + "，account = " + account + ", password = " + password);
            return requestStatus;
        }

        User userInfo = userService.getUserInfoByAccount(account);
        if (userInfo == null || !password.equals(userInfo.getPassword())) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.ACCOUNT_PASSWORD_NOT_MATCH);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.ACCOUNT_PASSWORD_NOT_MATCH));
            logger.debug("match fail, user info from db:{}", userInfo);
            return requestStatus;
        }

        request.getSession().setAttribute("loginUserId", userInfo.getUserId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loginUserId", userInfo.getUserId());
        requestStatus.setData(jsonObject.toJSONString());
        logger.debug("login success, account:{}", account);
        return requestStatus;
    }

    /**
     * 获取用户信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.get_user_info, method = RequestMethod.GET)
    public RequestStatus getUserInfo(HttpServletRequest request, Integer userId){
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (userId == null) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，userId = " + userId);
            return requestStatus;
        }

        if (!validatorService.validateLogin(requestStatus, request)) {
            return requestStatus;
        }

        Integer loginUserId = userService.getLoginUserId(request);
        if (loginUserId.intValue() != userId.intValue() && !validatorService.validateAdminPermission(requestStatus, request)) {
            return requestStatus;
        }

        User userInfo = userService.getUserInfoById(userId);
        requestStatus.setData(JSON.toJSONString(userInfo));
        return requestStatus;
    }

    /**
     * 获取用户列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.get_user_list, method = RequestMethod.GET)
    public RequestStatus getUserList(HttpServletRequest request, Page page) {
        RequestStatus requestStatus = new RequestStatus();

        if (!validatorService.validateAdminPermission(requestStatus, request)) {
            return requestStatus;
        }

        List<User> userList = userService.getUserList(page);
        requestStatus.setData(JSON.toJSONString(userList));
        return requestStatus;
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.modify_user, method = RequestMethod.POST)
    public RequestStatus modifyUser(HttpServletRequest request, User user){
        logger.debug("modify user, user:{}", user);
        RequestStatus requestStatus = new RequestStatus();

        if (!validatorService.validateUserParam(requestStatus, user)
                || !validatorService.validateLogin(requestStatus, request)){
            return requestStatus;
        }

        Integer loginUserId = userService.getLoginUserId(request);
        int userId = user.getUserId();
        //仅在修改他人信息时需要校验权限，修改自己的信息无需校验权限。
        if (loginUserId.intValue() != userId) {
            validatorService.validateAdminPermission(requestStatus, request);
        }


        try {
            int result = userService.modifyUser(user);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("modify user success, user:{}.", user);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.remove_user, method = RequestMethod.POST)
    public RequestStatus removeUser(HttpServletRequest request, Integer userId){
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (userId == null) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，userId = null");
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = userService.removeUser(userId);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("remove user success, userId:{}.", userId);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }
}
