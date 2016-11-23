package team.t9001.saad.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.t9001.saad.common.Page;
import team.t9001.saad.dao.NoticeDao;
import team.t9001.saad.model.Notice;

import com.alibaba.fastjson.JSON;

/**
 * desc:
 * Created by lifeihu on 2016/11/17.
 */
@Service
public class NoticeService {
    private static final Logger logger = LoggerFactory.getLogger(NoticeService.class);

    @Autowired
    private NoticeDao noticeDao;

    /**
     * 获取列表
     * @return
     */
    public List<Notice> getList(Page page){
        List<Notice> list = noticeDao.getList(page);
        logger.info(JSON.toJSONString(list));
        return list;
    }

    public Notice getById(Integer noticeId){
    	return noticeDao.getById(noticeId);
    }
    
    /**
     * 添加
     * @param 
     */
    public int add(Notice notice) {
        return noticeDao.add(notice);
    }

    /**
     * 修改
     * @param 
     * @return
     */
    public int modify(Notice notice) {
        return noticeDao.modify(notice);
    }

    /**
     * 删除
     * @param 
     * @return
     */
    public int remove(Integer noticeId) {
        return noticeDao.remove(noticeId);
    }
}
