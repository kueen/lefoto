/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.model.content;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * 评论类
 *
 * @author Eric
 */
@Entity
@Table(name = "le_comment")
public class LeComment implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;
    @Column(name = "content")
    String content;
    @Column(name = "user_id")
    int userId;
    @Column(name = "user_name")
    String userName;
    @Column(name = "reply_comment_id")
    int replyCommentId;
    @Column(name = "reply_user_id")
    int replyUserId;
    @Column(name = "reply_user_name")
    String replyUserName;
    //objecType表示评论的对象类型，1表示为图片
    @Column(name = "object_type")
    int objectType;
    @Column(name = "object_id")
    int objectId;
    @Column(name = "object_user_id")
    int objectUserId;
    @Column(name = "object_user_name")
    String objectUserName;
    @Column(name = "channel")
    int channel = 1;
    @Column(name = "create_time")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date createTime = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getReplyCommentId() {
        return replyCommentId;
    }

    public void setReplyCommentId(int replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    public int getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(int replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getObjectUserId() {
        return objectUserId;
    }

    public void setObjectUserId(int objectUserId) {
        this.objectUserId = objectUserId;
    }

    public String getObjectUserName() {
        return objectUserName;
    }

    public void setObjectUserName(String objectUserName) {
        this.objectUserName = objectUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
