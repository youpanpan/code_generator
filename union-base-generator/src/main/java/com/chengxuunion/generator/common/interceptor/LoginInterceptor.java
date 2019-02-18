package com.chengxuunion.generator.common.interceptor;

import com.chengxuunion.generator.common.constant.Constants;
import com.chengxuunion.generator.common.util.SessionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author youpanpan
 * @Description:    登录拦截器
 * @Date:创建时间: 2019-01-21 15:34
 * @Modified By:
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object value = SessionUtils.getValue(Constants.USER);

        // 用户没有登录，则跳转到登录页
        if (value == null) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
