package com.chengxuunion.generator.common.config;

import com.alibaba.fastjson.JSON;
import com.chengxuunion.generator.common.model.ErrorCode;
import com.chengxuunion.generator.common.model.ResultCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Author:liweifeng
 * @Description:spring mvc Controller或RestController切面
 *  使用了spring web的框架集成gs-core-mvc jar重新启动服务并可实现在Http请求之前和之后进行业务处理
 *  1.请求前：链接、方法名、ip、类名、参数打印日志
 *  2.请求后：响应结果、请求时间打印日志，结果信息统一封装返回
 * @Date:Created in 14:21 2018/8/31
 * @Modified By:
 */
@Component
@Aspect
public class ControllerAspect {
    private static Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Value("${public.url}")
    private String publicUrl;

    @Value("${spring.profiles.active}")
    private String env;

    /**
     * 对进行了Controller注解和RestController注解的类进行切面
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "execution(* com.chengxuunion..controller..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取http request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取请求参数
        Map<String,String[]> paramMap = request.getParameterMap();
        String paramResult = requestParams(paramMap);
        // 获取请求方法名
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();
        
        String targetMethodFullName = joinPoint.getTarget().getClass().getName() + "." + targetMethod.getName();
        // 打印请求日志
        logger.info("request url=【{}】 method=【{}】 ip=【{}】 class_method=【{}】 values=【{}】",request.getRequestURL(),request.getMethod(),request.getRemoteAddr(),
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
                paramResult
        );

        //打印结果日志
        Long startTime = Long.valueOf(System.currentTimeMillis());
        ResponseBody responseBody = targetMethod.getAnnotation(ResponseBody.class);

        Object result = null;
        try {
            Long endTime = Long.valueOf(System.currentTimeMillis());
            result = joinPoint.proceed();
            Long useTime = Long.valueOf(endTime.longValue() - startTime.longValue());
            if(responseBody!=null) {
                String r = null;
                if(result != null){
                    r = JSON.toJSONString(result);
                }
                logger.info("response method =【{}】 result=【{}】 time=【{}】", targetMethodFullName,r,
                        useTime);
                if (result != null) {
                    return ResultCode.success(result);
                } else {
                    return ResultCode.fail(ErrorCode.BUSINESS_EXCEPTION, result);
                }
            } else {
                return result;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.error(throwable.getMessage(), throwable);
            if (responseBody != null) {
                return ResultCode.fail(ErrorCode.BUSINESS_EXCEPTION.getCode(), getCauseThrowable(throwable).getMessage(), null);
            }
            throw throwable;
        }

    }

    /**
     * 获取最底层的异常对象
     *
     * @param throwable
     * @return
     */
    private Throwable getCauseThrowable(Throwable throwable) {
        Throwable cause = throwable;
        while(throwable.getCause() != null) {
            cause = throwable.getCause();
            throwable = cause;
        }
        return cause;
    }

    /**
     * 请求参数封装成字符串
     * @param map
     * @return
     */
    @SuppressWarnings("rawtypes")
	private String requestParams(Map<String,String[]> map){
        StringBuffer sb = new StringBuffer();
        Set keSet=map.entrySet();
        for(Iterator itr = keSet.iterator(); itr.hasNext();){
            Map.Entry me=(Map.Entry)itr.next();
            Object ok=me.getKey();
            Object ov=me.getValue();
            String[] value=new String[1];
            if(ov instanceof String[]){
                value=(String[])ov;
            }else{
                value[0]=ov.toString();
            }
            for(int k=0;k<value.length;k++){
                sb.append(ok+":"+value[k]).append(" ");
            }
        }
        return sb.toString();
    }

}
