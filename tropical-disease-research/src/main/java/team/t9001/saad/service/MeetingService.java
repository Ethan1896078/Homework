package team.t9001.saad.service;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.t9001.saad.common.Page;
import team.t9001.saad.dao.MeetingDao;
import team.t9001.saad.model.Meeting;
import team.t9001.saad.model.Meeting;

import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/17.
 */
@Service
public class MeetingService {
    private static final Logger logger = LoggerFactory.getLogger(MeetingService.class);

    @Autowired
    private MeetingDao meetingDao;

    /**
     * 获取会议列表
     * @param page
     * @return
     */
    public List<Meeting> getMeetingList(Page page){
        List<Meeting> meetingList = meetingDao.getMeetingList(page);
        logger.info(JSON.toJSONString(meetingList));
        return meetingList;
    }

    /**
     * 添加会议
     * @param meeting
     */
    public int addMeeting(Meeting meeting) {
        return meetingDao.addMeeting(meeting);
    }

    /**
     * 修改会议
     * @param meeting
     * @return
     */
    public int modifyMeeting(Meeting meeting) {
        return meetingDao.modifyMeeting(meeting);
    }

    /**
     * 删除会议
     * @param meetingId
     * @return
     */
    public int removeMeeting(Integer meetingId) {
        return meetingDao.removeMeeting(meetingId);
    }

    /**
     * 根据会议id获取会议信息
     * @param meetingId
     * @return
     */
    public Meeting getMeetingInfoById(Integer meetingId) {
        return meetingDao.getMeetingInfoById(meetingId);
    }
}
