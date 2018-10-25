package nl.han.oose.tracks;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TracksController {

    @Inject
    TracksHandler tracksHandler;

    @GET()
    public Response tracksGetResponse(@QueryParam("forPlaylist") int playlistID, @QueryParam("token") String token) {

        try {
            return Response.status(Response.Status.OK).entity(tracksHandler.getAllTracksExceptFromCurrentPlaylist(playlistID, token)).build();
        } catch (TracksException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

}
