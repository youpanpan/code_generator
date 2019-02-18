package com.chengxuunion.generator.common.util;

import com.chengxuunion.util.string.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-22 09:00
 * @Modified By:
 */
public class RequestUtils {

    private RequestUtils() {

    }

    /**
     * 判断是否ajax请求
     *
     * @param request
     * @return  是ajax请求返回true，否则返回false
     */
    public static boolean isAjax(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");
        if (requestedWith != null && StringUtils.isEquals("XMLHttpRequest", requestedWith)) {
            return true;
        }

        return false;
    }
}
