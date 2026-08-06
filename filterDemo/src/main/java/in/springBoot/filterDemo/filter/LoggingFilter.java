package in.springBoot.filterDemo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@Component
@Order(2)
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestId = UUID.randomUUID().toString();

        httpResponse.setHeader("X-request-ID", requestId);

        System.out.println("Incoming request "
                + httpRequest.getMethod()
                + " "
                + httpRequest.getRequestURI());

        long startTime = System.currentTimeMillis();

        try {
            chain.doFilter(request, response);
        } finally {

            long apiResponseTime = System.currentTimeMillis() - startTime;
            System.out.println("Response status: " + httpResponse.getStatus());
            System.out.println("Responded in " + apiResponseTime + " ms");
        }


    }
}
