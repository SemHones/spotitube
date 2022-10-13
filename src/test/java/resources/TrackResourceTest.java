package resources;

import datasource.objects.Track;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import resources.dto.TrackResponseDTO;
import services.TrackService;
import services.UserService;
import services.exceptions.TokenException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

class TrackResourceTest {
    private TrackResource tracksResource;
    private TrackResponseDTO tracksResponseDTO;

    private UserService userService = mock(UserService.class);
    private TrackService trackService = mock(TrackService.class);

    private static final int PLAYLIST_ID = 1;
    private static final String TOKEN = "5431-321";
    private static final List<Track> EMPTY_TRACKS = new ArrayList<>();

    @BeforeEach
    void setup() {
        tracksResource = new TrackResource();
        tracksResource.setUserService(userService);
        tracksResource.setTrackService(trackService);

        tracksResponseDTO = new TrackResponseDTO();
        tracksResponseDTO.setTracks(EMPTY_TRACKS);
    }

    @Test
    void canGetTrack() {
        // Arrange
        Mockito.when(trackService.getTracksFromPlaylist(PLAYLIST_ID)).thenReturn(tracksResponseDTO);

        // Act
        Response response = tracksResource.getTracks(TOKEN, PLAYLIST_ID);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void throwExceptionGetTracks(){
        // Arrange
        Mockito.when(trackService.getTracksFromPlaylist(anyInt())).thenThrow(TokenException.class);

        // Act

        // Assert
        assertThrows(TokenException.class, () -> tracksResource.getTracks(TOKEN, anyInt()));
    }
}
