package Restpackage;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;


public class Server {

    public static final String BASE_URI = "http://localhost:42000/";
    public Server() {
        ResourceConfig resourceConfig = ResourceConfig.forApplication(new Restconfigurator());
        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);
    }

}