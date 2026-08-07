package in.springBoot.SpringInterceptors.config;

import in.springBoot.SpringInterceptors.interceptor.AuthenticateInterceptor;
import in.springBoot.SpringInterceptors.interceptor.AuthorizationInterceptor;
import in.springBoot.SpringInterceptors.interceptor.LoggingInterceptor;
import in.springBoot.SpringInterceptors.interceptor.ResponseTimeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    LoggingInterceptor loggingInterceptor;
    AuthenticateInterceptor authenticateInterceptor;
    AuthorizationInterceptor authorizationInterceptor;
    ResponseTimeInterceptor responseTimeInterceptor;

    public WebConfig(LoggingInterceptor loggingInterceptor,
                     AuthenticateInterceptor authenticateInterceptor,
                     AuthorizationInterceptor authorizationInterceptor,
                     ResponseTimeInterceptor responseTimeInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
        this.authenticateInterceptor = authenticateInterceptor;
        this.authorizationInterceptor = authorizationInterceptor;
        this.responseTimeInterceptor = responseTimeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(responseTimeInterceptor)
                .addPathPatterns("/api/**")
                .order(1);
        registry.addInterceptor(authenticateInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/login/**", "/public/**", "/admin/**")
                .order(2);
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/api/**", "/admin/**")
                .excludePathPatterns("/login/**", "/public/**")
                .order(3);
        registry.addInterceptor(loggingInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/admin/**")
                .order(4);

    }


}
