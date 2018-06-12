

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.MultipartConfigElement;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        int port = 80;
        Server server = new Server(port);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");


        ServletHolder fileUploadServletHolder = new ServletHolder(new MainServlet());
        fileUploadServletHolder.getRegistration().setMultipartConfig(new MultipartConfigElement("data/tmp"));
        context.addServlet(fileUploadServletHolder, "/");

        server.setHandler(context);
        server.start();
        server.join();
    }
}