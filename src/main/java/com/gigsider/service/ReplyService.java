package com.gigsider.service;

import com.gigsider.po.Reply;

import java.util.List;

public interface ReplyService {

    //新增回复
    public boolean addReply(Reply reply);

    //删除回复
    public boolean delReply(int id);

    //更新回复
    public boolean upReply(Reply reply);

    //获取目标帖子所有回复
    public List<Reply> getReplyByComunitiId(int comuniti_id);

    //目标帖子回复分页查询
    public List<Reply> getReplyByComunitiIdPage(int comuniti_id, int page, int limit);

}
