/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.model.media;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 相册类
 * @author Eric
 */
@Entity
@Table(name = "le_album")
public class LeAlbum implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "category_id")
    private int categoryId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "description")
    private String description;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_time;
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date update_time = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
