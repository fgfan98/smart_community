package com.gigsider.dao;

import com.gigsider.po.Reply;

import java.util.List;
import java.util.Map;

public interface ReplyDao {

    //新增回复
    public boolean insertReply(Reply reply);

    //删除回复
    public boolean deleteReply(int id);

    //通过 comuniti_id 删除回复
    public boolean deleteReplyByComunitiId(int comuniti_id);

    //更新回复
    public boolean updateReply(Reply reply);

    //获取目标帖子所有回复
    public List<Reply> queryReplyByComunitiId(int comuniti_id);

    //目标帖子回复分页查询
    public List<Reply> queryReplyByComunitiIdPage(Map<String,Object> data);

    //查询被举报的回复
    public List<Reply> queryReportedReply();

    //举报回复
    public boolean reportReply(int id);

    //取消举报
    public boolean unReportReply(int id);

}
