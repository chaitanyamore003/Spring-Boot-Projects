package in.springboot;

import in.springboot.config.WebConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class Main {

    /**
     * Entry point of the application.
     *
     * This program manually creates:
     * 1. An Embedded Tomcat Server
     * 2. A Spring IoC Container
     * 3. A DispatcherServlet
     * 4. Maps all incoming requests to Spring MVC
     *
     * This is essentially what Spring Boot does automatically behind the scenes.
     */
    public static void main(String[] args) throws LifecycleException {

        // ============================================================
        // STEP 1 : Create an Embedded Tomcat Server
        // ============================================================

        Tomcat tomcat = new Tomcat();

        // Configure the port on which Tomcat will listen.
        tomcat.setPort(8080);

        // Creates the default HTTP connector.
        // Without this, Tomcat won't accept HTTP requests.
        tomcat.getConnector();

        // ============================================================
        // STEP 2 : Configure the Web Application Context
        // ============================================================

        // Root context path.
        // "" means the application will run at:
        // http://localhost:8080/
        String contextPath = "";

        // Absolute path of the web application's document root.
        // user.dir -> Current project directory
        String baseDoc = new File("SpringMVCDemo/src/main/webapp").getAbsolutePath();

        // Register the web application with Tomcat.
        Context context = tomcat.addContext(contextPath, baseDoc);

        // ============================================================
        // STEP 3 : Create the Spring IoC Container
        // ============================================================

        // Creates a WebApplicationContext capable of managing
        // Spring MVC beans such as Controllers, Services, etc.
        AnnotationConfigWebApplicationContext springContext =
                new AnnotationConfigWebApplicationContext();

        // Register the Java Configuration class.
        // WebConfig should contain @Configuration,
        // @ComponentScan, @EnableWebMvc, etc.
        springContext.register(WebConfig.class);


        // ============================================================
        // STEP 4 : Create DispatcherServlet
        // ============================================================

        // DispatcherServlet is the Front Controller of Spring MVC.
        // Every incoming HTTP request first reaches this servlet.
        DispatcherServlet dispatcherServlet =
                new DispatcherServlet(springContext);

        // Register DispatcherServlet with Embedded Tomcat.
        Tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet);

        // Map every incoming request to DispatcherServlet.
        // Example:
        // http://localhost:8080/home
        // http://localhost:8080/users
        // http://localhost:8080/api/products
        context.addServletMappingDecoded("/", "dispatcherServlet");

        // ============================================================
        // STEP 5 : Start the Embedded Tomcat Server
        // ============================================================

        tomcat.start();

        System.out.println("=======================================");
        System.out.println("Embedded Tomcat Started Successfully!");
        System.out.println("Server running at: http://localhost:"
                + tomcat.getConnector().getLocalPort());
        System.out.println("=======================================");

        // ============================================================
        // STEP 6 : Keep the Server Running
        // ============================================================

        // Without this line, the JVM would terminate immediately
        // after starting Tomcat.
        tomcat.getServer().await();
    }
}