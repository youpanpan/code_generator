package com.chengxuunion.generator.business.user.service;

import com.chengxuunion.generator.common.model.PageResult;

import com.chengxuunion.generator.business.user.model.User;
import com.chengxuunion.generator.business.user.model.request.UserPageParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @Author youpanpan
 * @Description:    用户服务接口
 * @Date:创建时间: 2019-01-16 13:54:23
 * @Modified By:
 */
public interface UserService {

    /**
     * 查询用户分页列表
     *
     * @param userPageParam  参数对象
     * @return  用户列表
     */
    PageResult<User> listUserPage(UserPageParam userPageParam);

    /**
     * 根据主键查询单个用户对象
     *
     * @param id 主键
     * @return  单个用户对象
     */
    User getUser(Long id);

    /**
     * 根据用户名或邮箱查询用户信息
     *
     * @param account
     * @return
     */
    User getUserByUserNameOrEmail(String account);

    /**
     * 保存用户对象
     *
     * @param user 用户对象
     * @return  保存影响的记录数
     */
    int saveUser(User user);

    /**
     * 更新用户对象
     *
     * @param user 用户对象
     * @return  更新影响的记录数
     */
    int updateUser(User user);

    /**
     * 重置用户密码
     *
     * @param id
     * @return
     */
    int resetPassword(Long id);

    /**
     * 根据主键删除用户
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteUser(Long id);

    /**
     * 上传头像
     *
     * @param file
     * @return
     */
    Map<String, Object> uploadHeadPhoto(MultipartFile file);

    /**
     * 下载头像
     *
     * @param id
     * @param response
     * @return
     */
    Boolean downloadHeadPhoto(Long id, HttpServletResponse response);
}