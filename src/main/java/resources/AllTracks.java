package resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import resources.dto.TrackResponseDTO;
import services.TrackService;
import services.UserService;

@Path("/tracks")
public class AllTracks {
    private TrackService trackService;
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracks(@QueryParam("forPlaylist") int playlistID, @QueryParam("token") String token) {
        userService.verifyToken(token);
        TrackResponseDTO tracksResponseDTO = trackService.getTracksOutsidePlaylist(playlistID);
        return Response.ok(tracksResponseDTO).build();
    }

    @Inject
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
