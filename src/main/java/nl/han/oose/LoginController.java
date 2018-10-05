package nl.han.oose;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class LoginController {

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginResponse(LoginCredentials creds) {
        //try catch block
        loginHandler handler = new loginHandler();
        try {
            return Response.status(Response.Status.OK).entity(handler.login(creds)).build();
        } catch (LoginException e) {
            String message = e.getMessage();
            return Response.status(Response.Status.UNAUTHORIZED).entity(message).build();
        }
    }
}
