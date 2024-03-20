package Restpackage;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class Restconfigurator extends Application {
 @Override
    public Set<Class<?>> getClasses(){
     HashSet<Class<?>> h = new HashSet<>();
     h.add(RestService.class);
     return h;
 }
}
