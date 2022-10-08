package resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import resources.dto.PlaylistsResponseDTO;
import services.PlaylistService;
import services.UserService;

@Path("/playlists")
public class PlaylistResource {

    private PlaylistService playlistService;
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token){
        int userID = userService.verifyToken(token);
        PlaylistsResponseDTO playlistsResponseDTO = playlistService.getPlaylists(userID);
        return Response.ok(playlistsResponseDTO).build();
    }

    @Inject
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
