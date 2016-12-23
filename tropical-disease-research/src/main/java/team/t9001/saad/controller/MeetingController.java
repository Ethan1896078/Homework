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
import team.t9001.saad.model.Meeting;
import team.t9001.saad.model.Meeting;
import team.t9001.saad.service.MeetingService;
import team.t9001.saad.service.ValidatorService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/16.
 */
@Controller
@RequestMapping("/meeting")
public class MeetingController {
    private static final Logger logger = LoggerFactory.getLogger(MeetingController.class);

    @Autowired
    private MeetingService meetingService;
    @Autowired
    private ValidatorService validatorService;

    /**
     * 添加会议
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.add_meeting, method = RequestMethod.POST)
    public RequestStatus addMeeting(HttpServletRequest request, Meeting meeting){
        logger.debug("add meeting begin, meeting: {}", meeting);
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (meeting == null
                || meeting.getTitle() == null
                || meeting.getConveneTime() == null
                || meeting.getContent() == null
                || meeting.getStatus() < 0) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，meeting = " + meeting);
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = meetingService.addMeeting(meeting);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("add meeting success, meeting:{}.", meeting);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }

    /**
     * 获取会议列表
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.get_meeting_list, method = RequestMethod.GET)
    public RequestStatus getMeetingList(HttpServletRequest request, Page page){
        RequestStatus requestStatus = new RequestStatus();
        if (!validatorService.validateLogin(requestStatus, request)) {
            return requestStatus;
        }

        if (!validatorService.validatePage(requestStatus, page)) {
            return requestStatus;
        }

        List<Meeting> meetingList = meetingService.getMeetingList(page);
        logger.info("get meeting list, page:{}, data:{}", page, meetingList);

        requestStatus.setData(JSON.toJSONString(meetingList));
        return requestStatus;
    }

    /**
     * 获取会议信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.get_meeting_info, method = RequestMethod.GET)
    public RequestStatus getMeetingInfo(HttpServletRequest request, Integer meetingId){
        RequestStatus requestStatus = new RequestStatus();
        //校验参数
        if (meetingId == null) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，meetingId = " + meetingId);
            return requestStatus;
        }

        if (!validatorService.validateLogin(requestStatus, request)) {
            return requestStatus;
        }

        Meeting meetingInfo = meetingService.getMeetingInfoById(meetingId);
        logger.info("get meeting info, data:{}", meetingInfo);

        requestStatus.setData(JSON.toJSONString(meetingInfo));
        return requestStatus;
    }

    /**
     * 修改会议
     * @param meeting
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.modify_meeting, method = RequestMethod.POST)
    public RequestStatus modifyMeeting(HttpServletRequest request, Meeting meeting){
        logger.debug("modify meeting begin.");
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (meeting == null
                || meeting.getMeetingId() <= 0
                || meeting.getTitle() == null
                || meeting.getConveneTime() == null
                || meeting.getContent() == null
                || meeting.getStatus() < 0) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，meeting = " + meeting);
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = meetingService.modifyMeeting(meeting);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("modify meeting success, meeting:{}.", meeting);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }

    /**
     * 删除会议
     * @param meetingId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlConstants.remove_meeting, method = RequestMethod.POST)
    public RequestStatus removeMeeting(HttpServletRequest request, Integer meetingId){
        RequestStatus requestStatus = new RequestStatus();

        //校验参数
        if (meetingId == null) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.INPUT_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.INPUT_ERROR) + "，meetingId = null");
            return requestStatus;
        }

        //校验权限
        if (!validatorService.validateAdminPermission(requestStatus, request)){
            return requestStatus;
        }

        try {
            int result = meetingService.removeMeeting(meetingId);
            if (result != 1) {
                requestStatus.setStatus(Constants.STATUS_NOT_OK);
                requestStatus.setErrorCode(ErrorCode.NO_ROW_AFFECTED);
                requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.NO_ROW_AFFECTED));
            }
            logger.info("remove meeting success, meetingId:{}.", meetingId);
        } catch (Exception e) {
            requestStatus.setStatus(Constants.STATUS_NOT_OK);
            requestStatus.setErrorCode(ErrorCode.DB_ACCESS_ERROR);
            requestStatus.setErrorMsg(ErrorCode.ERROR_MSG_MAP.get(ErrorCode.DB_ACCESS_ERROR));
        }
        return requestStatus;
    }
}
