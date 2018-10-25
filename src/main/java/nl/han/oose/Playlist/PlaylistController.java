package nl.han.oose.Playlist;


import nl.han.oose.entity.TrackDB;
import nl.han.oose.tracks.TrackReturn;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    @Inject
    private PlaylistHandler handler;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response playListsGetResponse(@QueryParam("token") String token) {

        PlaylistReturn playlistReturn = new PlaylistReturn(handler.getPlayListStorage(token), handler.returnTotalLength());

        try {
            return Response.status(Response.Status.OK).entity(playlistReturn).build();
        } catch (PlaylistException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksForPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistID) {

        TrackReturn trackReturn = new TrackReturn(handler.getAllTracksForThisPlaylist(token, playlistID));
        try {
            return Response.status(Response.Status.OK).entity(trackReturn).build();
        } catch (PlaylistException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response InsertTrackIntoPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistID, TrackDB track) {

        TrackReturn trackReturn = new TrackReturn(handler.addTracksToGivenPlaylist(token, playlistID, track));
        try {
            return Response.status(Response.Status.OK).entity(trackReturn).build();
        } catch (PlaylistException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @DELETE()
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response playListsDeleteResponse(@QueryParam("token") String token, @PathParam("id") int id) {

        PlaylistReturn playlistReturn = new PlaylistReturn(handler.deletePlaylistAndReturnAllPlaylists(token, id), handler.returnTotalLength());

        try {
            return Response.status(Response.Status.OK).entity(playlistReturn).build();
        } catch (PlaylistException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response playListsAddResponse(@QueryParam("token") String token, Playlist playlist) {

        PlaylistReturn playlistReturn = new PlaylistReturn(handler.addNewPlaylistAndReturnAllPlaylists(token, playlist), handler.returnTotalLength());

        try {
            return Response.status(Response.Status.OK).entity(playlistReturn).build();
        } catch (PlaylistException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response playListsEditResponse(@QueryParam("token") String token, @PathParam("id") int id, Playlist playlist) {

        PlaylistReturn playlistReturn = new PlaylistReturn(handler.editPlaylistAndReturnAllPlaylists(token, id, playlist), handler.returnTotalLength());

        try {
            return Response.status(Response.Status.OK).entity(playlistReturn).build();
        } catch (PlaylistException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

}
