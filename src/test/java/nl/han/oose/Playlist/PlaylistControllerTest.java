package nl.han.oose.Playlist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistControllerTest {

    @InjectMocks
    PlaylistController sut;

    @Mock
    PlaylistService service;


    @Test
    public void playListsGetResponseWhenGivenTokenIsCorrect() {
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(new Playlist(1, "nice rock", true, new String[]{}));

        PlaylistReturn playlistReturn = new PlaylistReturn(playlists, 5000);

        Mockito.when(service.getPlayListStorage(Mockito.any())).thenReturn(playlists);
        Mockito.when(service.returnTotalLength()).thenReturn(5000);
        Response response = sut.playListsGetResponse(Mockito.any());

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        PlaylistReturn resultPlaylistReturn = (PlaylistReturn) response.getEntity();

        assertEquals(playlistReturn.getPlaylists(), resultPlaylistReturn.getPlaylists());

        assertEquals(playlistReturn.getLength(), resultPlaylistReturn.getLength());
    }

    @Test
    public void playListsGetResponseWhenGivenTokenIsNotCorrect() {

        Mockito.when(service.getPlayListStorage(Mockito.any())).thenThrow(new PlaylistException("The token does not exist. Please log in!"));

        Response response = sut.playListsGetResponse(Mockito.any());

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }
}