package nl.han.oose.Playlist;

import nl.han.oose.entity.dto.PlaylistDTO;
import nl.han.oose.entity.dto.PlaylistWithLengthDTO;
import nl.han.oose.exceptions.PlaylistException;
import nl.han.oose.rest.PlaylistController;
import nl.han.oose.service.PlaylistService;
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
        List<PlaylistDTO> playlistDTOS = new ArrayList<>();
        playlistDTOS.add(new PlaylistDTO(1, "nice rock", true, new String[]{}));

        PlaylistWithLengthDTO playlistWithLengthDTO = new PlaylistWithLengthDTO(playlistDTOS, 5000);

        Mockito.when(service.getPlayListStorage(Mockito.any())).thenReturn(playlistDTOS);
        Mockito.when(service.returnTotalLength()).thenReturn(5000);
        Response response = sut.playListsGetResponse(Mockito.any());

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        PlaylistWithLengthDTO resultPlaylistWithLengthDTO = (PlaylistWithLengthDTO) response.getEntity();

        assertEquals(playlistWithLengthDTO.getPlaylists(), resultPlaylistWithLengthDTO.getPlaylists());

        assertEquals(playlistWithLengthDTO.getLength(), resultPlaylistWithLengthDTO.getLength());
    }

    @Test
    public void playListsGetResponseWhenGivenTokenIsNotCorrect() {

        Mockito.when(service.getPlayListStorage(Mockito.any())).thenThrow(new PlaylistException("The token does not exist. Please log in!"));

        Response response = sut.playListsGetResponse(Mockito.any());

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }
}