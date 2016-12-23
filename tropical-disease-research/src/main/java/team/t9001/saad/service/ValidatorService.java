package team.t9001.saad.service;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.t9001.saad.common.Constants;
import team.t9001.saad.common.ErrorCode;
import team.t9001.saad.common.Page;
import team.t9001.saad.common.RequestStatus;
import team.t9001.saad.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * desc:信息校验
 * Created by huangzhe on 2016/11/19.
 */
@Service
public class ValidatorService {
    private static final Logger logger = LoggerFactory.getLogger(ValidatorService.class);

    @Autowired
    private UserService userService;

    /**
     * 校验用户参数
     * @param user
     * @return
     */
    public boolean validateUserParam(RequestStatus requestStatus, User user){
        //校验参数
        if (user == null
                || StringUtils.isBlank(user.getUsername())
                || user.getType() < 0
                || user.getStatus() < 0
                || StringUtils.isBlank(user.getAccount())
                || StringUtils.isBlank(user.getPassword())) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(Constants.STATUS_NOT_OK) + "，user = " + JSON.toJSONString(user));
            return false;
        }
        return true;
    }

    /**
     * 校验管理员权限
     * @param requestStatus
     * @param request
     * @return
     */
    public boolean validateAdminPermission(RequestStatus requestStatus, HttpServletRequest request){
        if (!validateLogin(requestStatus, request)) {
            return false;
        }

        Integer loginUserId = userService.getLoginUserId(request);

        logger.debug("loginUserId = {}", loginUserId);
        User userInfo = userService.getUserInfoById(loginUserId);

        if(userInfo == null
                || (userInfo.getType() != Constants.USER_TYPE_ADMIN
                        && userInfo.getType() != Constants.USER_TYPE_SUPER_ADMIN)){
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.PERMISSION_DENIED);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.PERMISSION_DENIED) + "，userId = " + loginUserId);
            logger.warn("permission denied, loginUserId = {}", loginUserId);
            return false;
        }
        return true;
    }

    /**
     * 校验登录
     * @param requestStatus
     * @param request
     * @return
     */
    public boolean validateLogin(RequestStatus requestStatus, HttpServletRequest request) {
        Integer loginUserId = userService.getLoginUserId(request);
        if (loginUserId == null) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.USER_NOT_LOGIN);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.USER_NOT_LOGIN));
            logger.warn("user not login.");
            return false;
        }

        User userInfo = userService.getUserInfoById(loginUserId);
        if (userInfo == null){
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.ENTITY_NOT_FOUND);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.ENTITY_NOT_FOUND) + "，userId = " + loginUserId);
            logger.warn("login user not found, loginUserId = {}", loginUserId);
            return false;
        }

        if (userInfo.getStatus() != Constants.STATUS_OK){
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.ENTITY_STATUS_NOT_OK);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.ENTITY_STATUS_NOT_OK) + "，userId = " + loginUserId);
            logger.warn("login user is unavailable, loginUserId = {}", loginUserId);
            return false;
        }

        return true;
    }

    /**
     * 校验分页参数
     * @param page
     * @return
     */
    public boolean validatePage(RequestStatus requestStatus, Page page) {
        if (page == null || page.getCurrentPage() < 0 || page.getPageSize() <= 0) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，page = " + JSON.toJSONString(page));
            logger.warn("input params error, page = {}", JSON.toJSONString(page));
            return false;
        }
        return true;
    }

}
