package RouteFinder;

import java.awt.Dimension;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;

/**
 * Web Application
 * Runs the server that will accept requests for a route
 * Uses Grizzly
 * See RouteResource.java for response to request
 * Code taken from Jersey tutorial
 */
public class App {

    private static final URI BASE_URI = URI.create("http://localhost:8081/base/");
    public static final String ROOT_PATH = "route";
   
    public static void main(String[] args) {
        try {
        	
            final ResourceConfig resourceConfig = new ResourceConfig(RouteResource.class);
            
           Map<String, Object> map = new HashMap<String,Object>();
           // map.put("ContainerResponseFilters",)
           
         //   resourceConfig.getProperties().put(
          //      "com.sun.jersey.spi.container.ContainerResponseFilters",
           //     "com.sun.jersey.api.container.filter.LoggingFilter;com.RouteFinder.CrossDomainFilter"
          //  );
            CrossDomainFilter filter = new CrossDomainFilter();
            
            map.put("com.sun.jersey.spi.container.ContainerResponseFilters",filter.getClass());
            map.put("com.sun.jersey.api.container.filter.LoggingFilter",filter.getClass());
            resourceConfig.addProperties(map);
            System.out.println(resourceConfig.getProperties());
            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig);
            
            System.out.println(String.format("Application started.\nTry out %s%s\nHit enter to stop it...",
                    BASE_URI, ROOT_PATH));
            //Server runs until input is recieved
            System.in.read();
            server.stop();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
