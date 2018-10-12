package nl.han.oose.Playlist;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    @Inject
    private PlaylistHandler handler;

    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Response playListsResponse(@QueryParam("token") String token) {

        PlaylistReturn playlistReturn = new PlaylistReturn(handler.getPlayListStorage(token));

        try {
            return Response.status(Response.Status.OK).entity(playlistReturn).build();
        } catch (PlaylistException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }
}
