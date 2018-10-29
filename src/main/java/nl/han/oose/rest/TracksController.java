package nl.han.oose.rest;

import nl.han.oose.entity.dto.TrackDTO;
import nl.han.oose.exceptions.TracksException;
import nl.han.oose.service.TracksService;

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

        try {
            TrackDTO trackDTO = new TrackDTO(tracksService.getAllTracksExceptFromCurrentPlaylist(playlistID, token));
            return Response.status(Response.Status.OK).entity(trackDTO).build();
        } catch (TracksException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }
}
