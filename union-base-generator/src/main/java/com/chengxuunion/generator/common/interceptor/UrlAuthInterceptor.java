package com.chengxuunion.generator.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.chengxuunion.generator.business.url.model.Url;
import com.chengxuunion.generator.business.url.service.UrlService;
import com.chengxuunion.generator.business.user.model.User;
import com.chengxuunion.generator.common.constant.Constants;
import com.chengxuunion.generator.common.model.ErrorCode;
import com.chengxuunion.generator.common.model.ResultCode;
import com.chengxuunion.generator.common.util.RequestUtils;
import com.chengxuunion.generator.common.util.SessionUtils;
import com.chengxuunion.util.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-21 16:20
 * @Modified By:
 */
@Component
public class UrlAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UrlService urlService;

    @Value("${public.url}")
    private String publicUrl;

    @Value("${spring.profiles.active}")
    private String env;

    @SuppressWarnings("unchecked")
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 开发环境则直接跳过验证
        if (StringUtils.isEquals(Constants.DEV, env)) {
            return true;
        }

        // 请求地址
        String requestUrl = request.getRequestURI();
        String contextPath = request.getContextPath();
        if (StringUtils.isNotEmpty(contextPath)) {
            requestUrl = requestUrl.replace(contextPath, "");
        }
        if (isPublicUrl(requestUrl)) {
            return true;
        }
        
        User user = SessionUtils.getUser();

        // 获取用户能访问的URL列表
        Object value = SessionUtils.getValue(Constants.URL_LIST);
        List<Url> urlList = null;
        if (value == null) {
            urlList = urlService.listUrlByUserId(user.getId());
            SessionUtils.setValue(Constants.URL_LIST, urlList);
        } else {
            urlList = (List<Url>)value;
        }
        
        // 判断请求地址是否有权限访问
        //requestUrl = requestUrl.replaceAll("\\{.*?\\}", ".*");
        boolean isAuth = false;
        for (Url url : urlList) {
        	String urlValue = url.getUrl().replaceAll("\\{.*?\\}", ".*");
        	Pattern pattern = Pattern.compile(urlValue);
            // /user/download/${userId}/${userid}/...
            // /user/download/89898988/884747/...
            if (pattern.matcher(requestUrl).matches()) {
                isAuth = true;
                break;
            }
        }
        if (!isAuth) {
            if (RequestUtils.isAjax(request)) {
                ResultCode resultCode = ResultCode.fail(ErrorCode.NOT_AUTH_EXCEPTION, null);
                System.out.println(JSON.toJSONString(resultCode));
                response.getOutputStream().write(JSON.toJSONString(resultCode).getBytes("utf-8"));
                response.getOutputStream().close();
            } else {
                response.sendRedirect("/login");
            }
        }

        return true;
    }

    /**
     * 请求的地址是否为公开地址
     *
     * @param requestUrl
     * @return
     */
    private boolean isPublicUrl(String requestUrl) {
        String[] publicUrlArr = publicUrl.split(Constants.SPLIT_STR);
        boolean isPublic = false;
        for (String url : publicUrlArr) {
            Pattern pattern = Pattern.compile(url);
            if (pattern.matcher(requestUrl).matches()) {
                isPublic = true;
                break;
            }
        }

        return isPublic;
    }
}
