package Restpackage;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Path("")
public class RestService {

    HashMap<Integer,Spielfeld> gamemap=new HashMap<>();
    @GET
    @Path("/game/{gid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHello(@PathParam("gid") String gid) {
        Spielfeld current= gamemap.getOrDefault(Integer.parseInt(gid),new Spielfeld());
        Gson gson=new Gson();
        String json=gson.toJson(current.spielfeld);
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity(json)
                .build();
    }
}

