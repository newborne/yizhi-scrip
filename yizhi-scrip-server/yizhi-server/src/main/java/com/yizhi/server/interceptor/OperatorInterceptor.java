//package com.yizhi.server.interceptor;
//
//import com.yizhi.common.util.UserThreadLocal;
//import org.apache.dubbo.config.annotation.DubboReference;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Arrays;
//
//@Component
//public class OperatorInterceptor implements HandlerInterceptor {
//
//    @DubboReference(version = "1.0.0")
//    private LogApi logApi;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String annotation = Arrays.toString(((HandlerMethod) handler).getMethod().getDeclaredAnnotations());
//        this.logApi.save(UserThreadLocal.get().getId(), request.getRequestURI(), annotation);
//        return true;
//    }
//}
