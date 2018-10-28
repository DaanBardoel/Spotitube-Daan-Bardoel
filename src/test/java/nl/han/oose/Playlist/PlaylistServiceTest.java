package nl.han.oose.Playlist;

import nl.han.oose.Persistence.IPlaylistDAO;
import nl.han.oose.Persistence.IPlaylistTrackDAO;
import nl.han.oose.Persistence.ITokenDAO;
import nl.han.oose.Persistence.ITrackDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceTest {

    @InjectMocks
    private PlaylistServiceImpl sut;

    @Mock
    private IPlaylistDAO playlistDAO;

    @Mock
    private ITokenDAO tokenDAO;

    @Mock
    private IPlaylistTrackDAO playlistTracksDAO;

    @Mock
    private ITrackDAO tracksDAO;

    @Test
    public void getPlayListStorageWithCorrectToken() {

    }

    @Test
    public void returnTotalLength() {

    }

    @Test
    public void addNewPlaylistAndReturnAllPlaylists() {

    }

    @Test
    public void deletePlaylistAndReturnAllPlaylists() {

    }

    @Test
    public void editPlaylistAndReturnAllPlaylists() {

    }

    @Test
    public void getAllTracksForThisPlaylist() {

    }

    @Test
    public void addTracksToGivenPlaylist() {

    }

    @Test
    public void deleteGivenTrackFromPlaylist() {

    }

    @Test
    public void doesTokenExistInList() {

    }
}