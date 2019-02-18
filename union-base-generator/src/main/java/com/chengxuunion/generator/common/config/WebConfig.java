package com.chengxuunion.generator.common.config;

import com.chengxuunion.generator.common.constant.Constants;
import com.chengxuunion.generator.common.interceptor.LoginInterceptor;
import com.chengxuunion.generator.common.interceptor.UrlAuthInterceptor;
import com.chengxuunion.generator.common.util.SessionUtils;
import com.chengxuunion.generator.common.util.SpringUtil;
import com.chengxuunion.util.string.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-19 14:33
 * @Modified By:
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login/**", "/static/**", "/error**", "/set-language/**");
        registry.addInterceptor(SpringUtil.getBean(UrlAuthInterceptor.class)).addPathPatterns("/**")
                .excludePathPatterns("/login/**", "/static/**", "/error**", "/", "/set-language/**");
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        /**
         * 序列换成json时,将所有的long变成string
         * 因为js中得数字类型不能包含所有的java long值
         */
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }

    @Bean
    public HttpPutFormContentFilter httpPutFormContentFilter() {
        return new HttpPutFormContentFilter();
    }


    /*-------------------------国际化-------------------*/
    @Bean
    public LocaleResolver localeResolver() {
        return new NativeLocaleResolver();
    }

    protected static class NativeLocaleResolver implements LocaleResolver{

        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            String language = request.getParameter(Constants.LANGUAGE);
            Locale locale = Locale.getDefault();
            if(!StringUtils.isEmpty(language)) {
                String[] split = language.split("_");
                locale = new Locale(split[0],split[1]);
            } else {
                Object value = request.getSession().getAttribute(Constants.LANGUAGE);
                if (value != null) {
                    language = value.toString();
                    String[] split = language.split("_");
                    locale = new Locale(split[0],split[1]);
                } else {
                    language = locale.toString();
                    if (!StringUtils.isEquals("zh_CN", language)
                            && !StringUtils.isEquals("en_US", language)) {
                        SessionUtils.setValue(Constants.LANGUAGE, "en_US");
                    } else {
                        SessionUtils.setValue(Constants.LANGUAGE, language);
                    }
                }
            }
            return locale;
        }

        @Override
        public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

        }
    }
}
