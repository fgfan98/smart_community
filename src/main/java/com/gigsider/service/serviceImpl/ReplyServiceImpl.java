package com.gigsider.service.serviceImpl;

import com.gigsider.dao.ReplyDao;
import com.gigsider.po.Reply;
import com.gigsider.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    ReplyDao replyDao;

    @Override
    public boolean addReply(Reply reply) {
        return replyDao.insertReply(reply);
    }

    @Override
    public boolean delReply(int id) {
        return replyDao.deleteReply(id);
    }

    @Override
    public boolean upReply(Reply reply) {
        return replyDao.updateReply(reply);
    }

    @Override
    public List<Reply> getReplyByComunitiId(int comuniti_id) {
        return replyDao.queryReplyByComunitiId(comuniti_id);
    }

    @Override
    public List<Reply> getReplyByComunitiIdPage(int comuniti_id, int page, int limit) {
        Map<String,Object> data = new HashMap<>();
        data.put("comuniti_id", comuniti_id);
        data.put("page", (page-1)*limit);
        data.put("limit", limit);
        return replyDao.queryReplyByComunitiIdPage(data);
    }

    @Override
    public boolean delReplyByComunitiId(int comuniti_id) {
        return replyDao.deleteReplyByComunitiId(comuniti_id);
    }

    @Override
    public List<Reply> getReportedReply() {
        return replyDao.queryReportedReply();
    }

    @Override
    public boolean reportReply(int id) {
        return replyDao.reportReply(id);
    }

    @Override
    public boolean unReportReply(int id) {
        return replyDao.unReportReply(id);
    }
}
