package nl.han.oose.rest;


import nl.han.oose.entity.Track;
import nl.han.oose.entity.dto.PlaylistDTO;
import nl.han.oose.entity.dto.PlaylistWithLengthDTO;
import nl.han.oose.entity.dto.TrackDTO;
import nl.han.oose.exceptions.PlaylistException;
import nl.han.oose.exceptions.TracksException;
import nl.han.oose.service.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    @Inject
    private PlaylistService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response playListsGetResponse(@QueryParam("token") String token) {

        try {
            PlaylistWithLengthDTO playlistWithLengthDTO = new PlaylistWithLengthDTO(service.getPlayListStorage(token), service.returnTotalLength());
            return Response.status(Response.Status.OK).entity(playlistWithLengthDTO).build();
        } catch (PlaylistException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksForPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistID) {

        try {
            TrackDTO trackDTO = new TrackDTO(service.getAllTracksForThisPlaylist(token, playlistID));
            return Response.status(Response.Status.OK).entity(trackDTO).build();
        } catch (TracksException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @Path("/{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertTrackIntoPlaylist(@PathParam("id") int id, @QueryParam("token") String token, Track track) {

        try {
            service.addTracksToGivenPlaylist(token, id, track);
            TrackDTO trackDTO = new TrackDTO(service.getAllTracksForThisPlaylist(token, id));
            return Response.status(Response.Status.OK).entity(trackDTO).build();
        } catch (PlaylistException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @Path("/{playlistid}/tracks/{trackid}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteNumberFromPlaylist(@PathParam("playlistid") int playlistID, @PathParam("trackid") int trackID, @QueryParam("token") String token) {

        try {
            service.deleteGivenTrackFromPlaylist(token, playlistID, trackID);
            TrackDTO trackDTO = new TrackDTO(service.getAllTracksForThisPlaylist(token, playlistID));
            return Response.status(Response.Status.OK).entity(trackDTO).build();
        } catch (PlaylistException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @DELETE()
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response playListsDeleteResponse(@QueryParam("token") String token, @PathParam("id") int id) {

        try {
            PlaylistWithLengthDTO playlistWithLengthDTO = new PlaylistWithLengthDTO(service.deletePlaylistAndReturnAllPlaylists(token, id), service.returnTotalLength());
            return Response.status(Response.Status.OK).entity(playlistWithLengthDTO).build();
        } catch (PlaylistException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response playListsAddResponse(@QueryParam("token") String token, PlaylistDTO playlistDTO) {

        try {
            PlaylistWithLengthDTO playlistWithLengthDTO = new PlaylistWithLengthDTO(service.addNewPlaylistAndReturnAllPlaylists(token, playlistDTO), service.returnTotalLength());
            return Response.status(Response.Status.OK).entity(playlistWithLengthDTO).build();
        } catch (PlaylistException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response playListsEditResponse(@QueryParam("token") String token, @PathParam("id") int id, PlaylistDTO playlistDTO) {


        try {
            PlaylistWithLengthDTO playlistWithLengthDTO = new PlaylistWithLengthDTO(service.editPlaylistAndReturnAllPlaylists(token, id, playlistDTO), service.returnTotalLength());
            return Response.status(Response.Status.OK).entity(playlistWithLengthDTO).build();
        } catch (PlaylistException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

}
