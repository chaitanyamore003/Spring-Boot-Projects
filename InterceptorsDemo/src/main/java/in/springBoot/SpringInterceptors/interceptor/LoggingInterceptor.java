package in.springBoot.SpringInterceptors.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("Request Entered Logging Interceptor");

        System.out.println("Incoming request ---------------");
        System.out.println("Http Method: " +  request.getMethod());
        System.out.println("Http request URI: " +  request.getRequestURI());
        System.out.println("Http request parameters: " +  request.getQueryString());
        System.out.println("Http request IP: " +  request.getRemoteAddr());
        System.out.println("Token header : " + request.getHeader("token"));


        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            String methodName = handlerMethod.getMethod().getName();
            String contollerName = handlerMethod.getBeanType().getName();
            System.out.println("preHandle called--------------");
            System.out.println("methodName: " + methodName);
            System.out.println("contollerName: " + contollerName);
        }
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("request Exited Logging Interceptor");
    }

}
