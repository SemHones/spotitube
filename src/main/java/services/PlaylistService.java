package services;

import datasource.dao.PlaylistDAO;
import datasource.objects.Playlist;
import resources.dto.PlaylistRequestDTO;
import resources.dto.PlaylistsResponseDTO;
import services.exceptions.UnauthorizedException;

public class PlaylistService {

    private PlaylistDAO playlistDAO = new PlaylistDAO();

    public PlaylistsResponseDTO getPlaylists(int userId){
        PlaylistsResponseDTO playlistsResponseDTO = new PlaylistsResponseDTO();

        playlistsResponseDTO.setPlaylists(playlistDAO.getAllPlaylistFromUser(userId));
        return playlistsResponseDTO;
    }

    public PlaylistsResponseDTO changePlaylistName(int userId, String name, int playlistId) throws UnauthorizedException {
        Playlist playlistObject = new Playlist(playlistId, name, userId);

        if(playlistDAO.checkPlaylistOwner(userId, playlistId)){
            playlistDAO.changePlaylistName(playlistObject);
            return getPlaylists(userId);
        }
        throw new UnauthorizedException();
    }
}
