package team.t9001.saad.service;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.t9001.saad.common.Page;
import team.t9001.saad.dao.NoticeDao;
import team.t9001.saad.model.Notice;
import team.t9001.saad.model.Notice;

import java.util.List;

/**
 * desc:
 * Created by huangzhe on 2016/11/17.
 */
@Service
public class NoticeService {
    private static final Logger logger = LoggerFactory.getLogger(NoticeService.class);

    @Autowired
    private NoticeDao noticeDao;

    /**
     * 获取公告列表
     * @param page
     * @return
     */
    public List<Notice> getNoticeList(Page page){
        List<Notice> noticeList = noticeDao.getNoticeList(page);
        logger.info(JSON.toJSONString(noticeList));
        return noticeList;
    }

    /**
     * 添加公告
     * @param notice
     */
    public int addNotice(Notice notice) {
        return noticeDao.addNotice(notice);
    }

    /**
     * 修改公告
     * @param notice
     * @return
     */
    public int modifyNotice(Notice notice) {
        return noticeDao.modifyNotice(notice);
    }

    /**
     * 删除公告
     * @param noticeId
     * @return
     */
    public int removeNotice(Integer noticeId) {
        return noticeDao.removeNotice(noticeId);
    }

    /**
     * 根据公告id获取公告信息
     * @param noticeId
     * @return
     */
    public Notice getNoticeInfoById(Integer noticeId) {
        return noticeDao.getNoticeInfoById(noticeId);
    }
}
