/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.service.iface.user;

import com.lefoto.model.user.LeDefaultUserFace;
import com.lefoto.model.user.LeRelationship;
import com.lefoto.model.user.LeUser;
import com.lefoto.model.user.LeUserInfo;
import com.lefoto.model.user.LeUserStatus;
import java.util.List;

/**
 * 用户基本操作的Service类
 *
 * @author Eric
 */
public interface UserService {

    /**
     * 添加用户
     *
     * @param user
     */
    public void addUser(LeUser user);

    /**
     * 删除用户
     *
     * @param user
     */
    public void delUser(LeUser user);

    /**
     * 根据Email获取用户
     *
     * @param email
     * @return
     */
    public LeUser findUserByEmail(String email);

    /**
     * 根据Id获取用户
     *
     * @param userId
     * @return
     */
    public LeUser finUserById(int userId);

    /**
     * 获取所有用户
     *
     * @return
     */
    public List<LeUser> findAllUsers();

    /**
     * 更新用户
     *
     * @param user
     */
    public void updateUser(LeUser user);

    /**
     * 更新用户头像
     *
     * @param userFace
     * @param userId
     */
    public void updateUserFace(String userFace, int userId);

    /**
     * 检查用户邮箱和密码是否正确
     *
     * @param email
     * @param password
     * @return
     */
    public boolean checkUser(String email, String password);

    /**
     * 检查邮箱是否存在
     *
     * @param email
     * @return
     */
    public boolean checkEmailExist(String email);

    /**
     * 添加或者更新用户信息
     *
     * @param userInfo
     */
    public void addOrUpdateUserInfo(LeUserInfo userInfo);

    /**
     * 添加默认用户头像
     *
     * @param defaultUserFace
     */
    public void addDefaultUserFace(LeDefaultUserFace defaultUserFace);

    /**
     * 根据Id获取用户默认头像
     *
     * @param id
     * @return
     */
    public LeDefaultUserFace findDefaultUserFaceById(int id);

    /**
     * 获取所有的用户头像
     *
     * @return
     */
    public List<LeDefaultUserFace> findAllDefaultUserFace();

    /**
     * 获取随机用户头像
     *
     * @return
     */
    public LeDefaultUserFace findRandomDefaultUserFace();

    /**
     * 获取全部好友关系
     *
     * @return
     */
    public List<LeRelationship> findAllRelationships();

    /**
     * 更新用户状态表
     *
     * @param userStatus
     */
    public void updateUserStatus(LeUserStatus userStatus);

    /**
     * 获取全部用户状态数据
     *
     * @return
     */
    public List<LeUserStatus> findAllUserStatus();

    /**
     * 根据userId获取用户状态
     *
     * @param userId
     * @return
     */
    public LeUserStatus findUserStatus(int userId);
}
