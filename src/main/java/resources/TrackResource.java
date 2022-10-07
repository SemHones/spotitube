package resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import resources.dto.TrackResponseDTO;
import services.PlaylistService;
import services.TrackService;
import services.UserService;

@Path("/tracks")
public class TrackResource {
    private TrackService trackService;
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@QueryParam("playlistID") int playlistID, @QueryParam("token") String token) {
        userService.verifyToken(token);
        TrackResponseDTO tracksResponseDTO = trackService.getTracks(playlistID);
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
