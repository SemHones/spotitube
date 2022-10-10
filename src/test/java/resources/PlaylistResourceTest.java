package resources;

import datasource.objects.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import resources.dto.PlaylistsResponseDTO;
import services.exceptions.TokenException;
import services.PlaylistService;
import services.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PlaylistResourceTest {


    private static final String correct_token = "1234-1234";
    private static final String false_token = "1234-12343";
    private static final int HTTP_OK = 200;
    private static final int HTTP_UNAUTHORIZED = 403;
    private PlaylistResource sut;
    private PlaylistService mockedPlaylistService;
    private UserService mockedUserService;

    @BeforeEach
    void setup() {
        sut = new PlaylistResource();

        mockedPlaylistService = mock(PlaylistService.class);
        sut.setPlaylistService(mockedPlaylistService);

        mockedUserService = mock(UserService.class);
        sut.setUserService(mockedUserService);
    }

    @Test
    void getPlaylistInResponseOk() {
        // Arrange
        var itemsToReturn = new PlaylistsResponseDTO();
        List<Playlist> playlists = new ArrayList<Playlist>();

//        Playlist playlist1 = new Playlist();
//        playlist1.setName("playlist one");

//        playlists.add(playlist1);

        itemsToReturn.setPlaylists(playlists);

//        when(mockedUserService.verifyToken(anyString())).thenReturn(1);
//        when(mockedPlaylistService.getPlaylists()).thenReturn(itemsToReturn);

        // Act
        var response = sut.getPlaylists(correct_token);

        // Assert
        assertEquals(HTTP_OK, response.getStatus());
        assertEquals(itemsToReturn, response.getEntity());

    }

    //TODO: fix me
    @Test
    void getPlaylistWithResponseUnauthorized() {
        // Arrange
        doThrow(TokenException.class).when(sut.getPlaylists(anyString()));

        // ACt & Assert
        assertThrows(TokenException.class, () -> sut.getPlaylists(false_token));

    }
}
