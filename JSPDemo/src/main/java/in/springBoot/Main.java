package in.springBoot;

import in.springBoot.config.WebConfig1;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.apache.jasper.servlet.JasperInitializer;
import org.apache.jasper.servlet.JspServlet;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws LifecycleException {

        // Embedded Tomcat
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        String contextPath = "";
        String baseDoc = new File("JSPDemo/src/main/webapp").getAbsolutePath();

        Context context = tomcat.addContext("", baseDoc);

        // Enable JSP support
        context.addServletContainerInitializer(new JasperInitializer(), Set.of());

        Wrapper jspServlet = Tomcat.addServlet(context, "jsp", new JspServlet());
        jspServlet.setLoadOnStartup(1);

        context.addServletMappingDecoded("*.jsp", "jsp");
        context.addServletMappingDecoded("*.jspx", "jsp");

        // Spring IoC Container
        AnnotationConfigWebApplicationContext springContext =
                new AnnotationConfigWebApplicationContext();

        springContext.register(WebConfig1.class);
//        pringContext.setServletContext(context.getServletContext());
//        springContext.refresh();


        // Dispatcher Servlet
        DispatcherServlet dispatcherServlet =
                new DispatcherServlet(springContext);

        Wrapper dispatcher = Tomcat.addServlet(
                context,
                "dispatcherServlet",
                dispatcherServlet
        );

        dispatcher.setLoadOnStartup(1);
        dispatcher.setAsyncSupported(true);

        context.addServletMappingDecoded("/", "dispatcherServlet");


        // Start Tomcat
        tomcat.start();

        System.out.println("--------------------------------");
        System.out.println("Embedded Tomcat Started");
        System.out.println("http://localhost:" + tomcat.getConnector().getLocalPort());
        System.out.println("--------------------------------");

        tomcat.getServer().await();
    }
}