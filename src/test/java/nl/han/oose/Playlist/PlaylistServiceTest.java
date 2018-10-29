package nl.han.oose.Playlist;

import nl.han.oose.Persistence.IPlaylistDAO;
import nl.han.oose.Persistence.ITokenDAO;
import nl.han.oose.Persistence.ITrackDAO;
import nl.han.oose.entity.DTO.PlaylistDTO;
import nl.han.oose.entity.Token;
import nl.han.oose.exceptions.PlaylistException;
import nl.han.oose.service.PlaylistServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    private PlaylistServiceImpl sut;

    @Mock
    private IPlaylistDAO playlistDAO;

    @Mock
    private ITokenDAO tokenDAO;

    @Mock
    private ITrackDAO tracksDAO;

    @Test
    public void getPlayListStorageWithCorrectToken() {
        List<PlaylistDTO> playlistDTOS = new ArrayList<>();
        playlistDTOS.add(new PlaylistDTO(1, "nice rock", true, new String[]{}));
        Token token = new Token("12345678912345", 1, "31.12.2018 23:59:59");
        when(playlistDAO.getAllPlaylists(Mockito.any())).thenReturn(playlistDTOS);
        when(tokenDAO.getTokenForGivenTokenString(Mockito.any())).thenReturn(token);
        assertNotNull(sut.getPlayListStorage("12345678912345"));
    }

    @Test
    public void getPlayListStorageWithWrongToken() throws PlaylistException {
        thrown.expect(PlaylistException.class);
        thrown.expectMessage("The token does not exist. Please log in!");
        when(tokenDAO.getTokenForGivenTokenString(Mockito.any())).thenReturn(null);
        sut.getPlayListStorage(Mockito.any());
    }

    @Test
    public void returnTotalLength() {
        when(tracksDAO.getTotalDuration()).thenReturn(5000);
        assertEquals(sut.returnTotalLength(), 5000);
    }
}