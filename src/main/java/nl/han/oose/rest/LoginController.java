package nl.han.oose.rest;

import nl.han.oose.entity.dto.AccountDTO;
import nl.han.oose.exceptions.LoginException;
import nl.han.oose.service.LoginService;

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
    public Response loginResponse(AccountDTO creds) {
        //try catch block
        try {
            return Response.status(Response.Status.OK).entity(loginService.loginNewVersion(creds)).build();
        } catch (LoginException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }
}