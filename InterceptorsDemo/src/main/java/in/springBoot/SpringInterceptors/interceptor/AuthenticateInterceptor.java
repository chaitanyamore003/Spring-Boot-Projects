package in.springBoot.SpringInterceptors.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticateInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String apiKey = request.getHeader("x-api-key");
        System.out.println("Request Entered Authentication Interceptor");

        if(apiKey == null || !apiKey.equals("12345")){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("" +
                    "{\n" +
                    "    \"message\" : \"User is not Authenticated, please authenticate and try again\"\n" +
                    "}");
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Request Exited Authentication Interceptor");
    }
}
