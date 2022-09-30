package resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/hello")
public class helloWorld {
    @GET
    public String sayHello() {
        return "Hello World";
    }
}
