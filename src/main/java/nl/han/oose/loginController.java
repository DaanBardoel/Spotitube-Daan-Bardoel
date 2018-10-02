package nl.han.oose;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class loginController {

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(loginCredentials credentials) {
        if (credentials.getUsername().equals("daan") && credentials.getPassword().equals("password")) {
            Token token = new Token(credentials.getUsername(), "1234-1234-1234");
            return Response.status(Response.Status.OK).entity(token).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
