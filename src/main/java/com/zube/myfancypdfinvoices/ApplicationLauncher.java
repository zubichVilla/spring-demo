package com.zube.myfancypdfinvoices;

import com.zube.myfancypdfinvoices.context.MyFancyPdfInvoicesApplicationConfiguration;
import com.zube.myfancypdfinvoices.web.MyFancyPdfInvoicesServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;

public class ApplicationLauncher {

    public static void main (String[] args) throws LifecycleException {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context tomcatCtx = tomcat.addContext("", null);

        WebApplicationContext applicationContext = createApplicationContext(tomcatCtx.getServletContext());

        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);

        Wrapper servlet = Tomcat.addServlet(tomcatCtx, "dispatcherServlet", dispatcherServlet);
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");//bilo koja putanja

        tomcat.start();
    }

    public static WebApplicationContext createApplicationContext(ServletContext servletContext) {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MyFancyPdfInvoicesApplicationConfiguration.class);
        ctx.setServletContext(servletContext);
        ctx.refresh();
        ctx.registerShutdownHook();

        return ctx;
    }
}