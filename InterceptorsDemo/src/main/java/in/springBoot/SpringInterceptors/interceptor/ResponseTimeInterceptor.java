package in.springBoot.SpringInterceptors.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class ResponseTimeInterceptor implements HandlerInterceptor {

    long startTime;
    long duration;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Request Entered ResponseTimeInterceptor");
        startTime = System.currentTimeMillis();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        duration = System.currentTimeMillis() - startTime;
        System.out.println("Response time is " + duration);
        System.out.println("Request Exited Response Interceptor");
    }
}
