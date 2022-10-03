package services;

import datasource.objects.Playlist;
import resources.dto.PlaylistsResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class PlaylistService {


    public PlaylistsResponseDTO getPlaylists(int id){
        //TODO: implement user specific playlist
        PlaylistsResponseDTO playlistsResponseDTO = new PlaylistsResponseDTO();
        if(id == 1){
            playlistsResponseDTO.setPlaylists(getHardCodedPlaylist());
            return playlistsResponseDTO;
        }
        return null;
    }

    public List<Playlist> getHardCodedPlaylist(){
        List<Playlist> playlists = new ArrayList<Playlist>();

        Playlist playlist1 = new Playlist();
        playlist1.setName("playlist one");
        Playlist playlist2 = new Playlist();
        playlist2.setName("playlist two");
        Playlist playlist3 = new Playlist();
        playlist3.setName("playlist three");

        playlists.add(playlist1);
        playlists.add(playlist2);
        playlists.add(playlist3);

        return playlists;
    }
}
