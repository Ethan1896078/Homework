package team.t9001.saad.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.t9001.saad.common.*;
import team.t9001.saad.model.Notice;
import team.t9001.saad.model.Notice;
import team.t9001.saad.service.NoticeService;
import team.t9001.saad.service.ValidatorService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/16.
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private ValidatorService validatorService;

    /**
     * 添加公告
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.add_notice, method = RequestMethod.POST)
    public RequestStatus addNotice(HttpServletRequest request, Notice notice){
        logger.debug("add notice begin, notice: {}", notice);
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (notice == null
                || notice.getTitle() == null
                || notice.getContent() == null
                || notice.getStatus() < 0) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，notice = " + notice);
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = noticeService.addNotice(notice);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("add notice success, notice:{}.", notice);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }

    /**
     * 获取公告列表
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.get_notice_list, method = RequestMethod.GET)
    public RequestStatus getNoticeList(HttpServletRequest request, Page page){
        RequestStatus requestStatus = new RequestStatus();
        if (!validatorService.validateLogin(requestStatus, request)) {
            return requestStatus;
        }

        if (!validatorService.validatePage(requestStatus, page)) {
            return requestStatus;
        }

        List<Notice> noticeList = noticeService.getNoticeList(page);
        logger.info("get notice list, page:{}, data:{}", page, noticeList);

        requestStatus.setData(JSON.toJSONString(noticeList));
        return requestStatus;
    }

    /**
     * 获取公告信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.get_notice_info, method = RequestMethod.GET)
    public RequestStatus getNoticeInfo(HttpServletRequest request, Integer noticeId){
        RequestStatus requestStatus = new RequestStatus();
        //校验参数
        if (noticeId == null) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，noticeId = " + noticeId);
            return requestStatus;
        }

        if (!validatorService.validateLogin(requestStatus, request)) {
            return requestStatus;
        }

        Notice noticeInfo = noticeService.getNoticeInfoById(noticeId);
        logger.info("get notice info, data:{}", noticeInfo);

        requestStatus.setData(JSON.toJSONString(noticeInfo));
        return requestStatus;
    }

    /**
     * 修改公告
     * @param notice
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.modify_notice, method = RequestMethod.POST)
    public RequestStatus modifyNotice(HttpServletRequest request, Notice notice){
        logger.debug("modify notice begin.");
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (notice == null
                || notice.getNoticeId() <= 0
                || notice.getTitle() == null
                || notice.getContent() == null
                || notice.getStatus() < 0) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，notice = " + notice);
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = noticeService.modifyNotice(notice);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("modify notice success, notice:{}.", notice);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }

    /**
     * 删除公告
     * @param noticeId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.remove_notice, method = RequestMethod.POST)
    public RequestStatus removeNotice(HttpServletRequest request, Integer noticeId){
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (noticeId == null) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，noticeId = null");
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = noticeService.removeNotice(noticeId);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("remove notice success, noticeId:{}.", noticeId);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }
}
