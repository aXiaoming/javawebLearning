package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.HostReply;

// 主人回复
public interface HostReplyDAO {
    //根据replyId查询关联的HostReply实体
    HostReply getHostReplyByReplyId(Integer replyId);

    //删除特定的HostReply
    void delHostReply(Integer id);
}
