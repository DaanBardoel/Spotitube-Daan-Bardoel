package nl.han.oose.tracks;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TracksController {

    @Inject
    TracksService tracksService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response tracksGetResponse(@QueryParam("forPlaylist") int playlistID, @QueryParam("token") String token) {

        TrackReturn trackReturn = new TrackReturn(tracksService.getAllTracksExceptFromCurrentPlaylist(playlistID, token));

        try {
            return Response.status(Response.Status.OK).entity(trackReturn).build();
        } catch (TracksException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }
}
