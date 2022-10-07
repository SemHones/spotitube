package services;

import datasource.objects.Playlist;
import jakarta.ws.rs.core.Response;
import resources.dto.PlaylistsResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class PlaylistService {

    private final int HTTP_NOT_FOUND = 404;
    List<Playlist> playlists = new ArrayList<Playlist>();


    public PlaylistsResponseDTO getPlaylists(){
        //TODO: implement user specific playlist
        PlaylistsResponseDTO playlistsResponseDTO = new PlaylistsResponseDTO();

        playlistsResponseDTO.setPlaylists(getHardCodedPlaylist());
        return playlistsResponseDTO;
    }

    public List<Playlist> getHardCodedPlaylist(){
        TrackService ts = new TrackService();

        Playlist playlist1 = new Playlist();
        playlist1.setName("Metal");
        playlist1.setId(1);
//        playlist1 = ts.fillHardCodedPlaylistWithHardcodedTracks(playlist1);
        Playlist playlist2 = new Playlist();
        playlist2.setName("Liked songs");
        playlist2.setId(2);
        Playlist playlist3 = new Playlist();
        playlist3.setName("Top 40");
        playlist3.setId(3);

        playlists.add(playlist1);
        playlists.add(playlist2);
        playlists.add(playlist3);

        return playlists;
    }
}
