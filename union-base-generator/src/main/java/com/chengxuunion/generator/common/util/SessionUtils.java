package com.chengxuunion.generator.common.util;

import com.chengxuunion.generator.business.user.model.User;
import com.chengxuunion.generator.business.userrole.model.UserRole;
import com.chengxuunion.generator.common.constant.Constants;
import com.chengxuunion.util.string.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-21 11:38
 * @Modified By:
 */
public class SessionUtils {

    private SessionUtils() {

    }

    /**
     * 根据session键获取值
     *
     * @param key
     * @return
     */
    public static Object getValue(String key) {
        HttpServletRequest request = getRequest();
        return request.getSession().getAttribute(key);
    }

    /**
     * 往session中设置值
     *
     * @param key
     * @param value
     */
    public static void setValue(String key, Object value) {
        HttpServletRequest request = getRequest();
        request.getSession().setAttribute(key, value);
    }

    /**
     * 移除session中的某个属性值
     *
     * @param key
     */
    public static void remove(String key) {
        HttpServletRequest request = getRequest();
        request.getSession().removeAttribute(key);
    }

    /**
     * 判断是否已登录
     *
     * @return
     */
    public static boolean isLogin() {
        Object value = getRequest().getSession().getAttribute(Constants.USER);
        return value != null;
    }

    /**
     * 获取登录用户对象
     *
     * @return
     */
    public static User getUser() {
        Object value = getRequest().getSession().getAttribute(Constants.USER);
        return (User)value;
    }

    /**
     * 判断登录用户是否为管理员
     *
     * @return  是管理员返回true，否返回false
     */
    public static boolean isAdmin() {
        User user = getUser();
        List<UserRole> userRoleList = user.getUserRoleList();
        boolean isAdmin = false;
        for (UserRole userRole : userRoleList) {
            if (StringUtils.isEquals(userRole.getRole().getRoleCode(), Constants.ROLE_ADMIN)) {
                isAdmin = true;
                break;
            }
        }

        return isAdmin;
    }

    /**
     * 获取HttpServletRequest对象
     *
     * @return
     */
    private static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        return request;
    }

}
