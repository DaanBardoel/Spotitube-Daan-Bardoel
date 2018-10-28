package nl.han.oose.Login;

import nl.han.oose.Playlist.PlaylistException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    @Inject
    private LoginService loginService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginResponse(LoginCredentials creds) {
        //try catch block
        try {
            return Response.status(Response.Status.OK).entity(loginService.loginNewVersion(creds)).build();
        } catch (PlaylistException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }
}