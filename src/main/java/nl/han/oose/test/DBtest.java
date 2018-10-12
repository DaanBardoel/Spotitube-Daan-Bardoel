package nl.han.oose.test;

import nl.han.oose.Persistence.AccountDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/test")
public class DBtest {

    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Response testDbResponse() {
        AccountDAO dao = new AccountDAO();
        return Response.status(Response.Status.OK).entity(dao.getAllAccounts()).build();
    }
}
