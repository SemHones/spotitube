package services;

import datasource.dao.PlaylistDAO;
import datasource.objects.Playlist;
import resources.dto.PlaylistsResponseDTO;
import services.exceptions.UnauthorizedException;

public class PlaylistService {

    private PlaylistDAO playlistDAO = new PlaylistDAO();

    public PlaylistsResponseDTO getPlaylists(int userId){
        PlaylistsResponseDTO playlistsResponseDTO = new PlaylistsResponseDTO();

        playlistsResponseDTO.setPlaylists(playlistDAO.getAllPlaylistFromUser(userId));
        return playlistsResponseDTO;
    }

    public PlaylistsResponseDTO editPlaylistName(int playlistId, String name, int userId) throws UnauthorizedException {
        Playlist playlistObject = new Playlist(playlistId, name, userId);

        if(playlistDAO.checkPlaylistOwner(userId, playlistId)){
            playlistDAO.editPlaylistName(playlistObject);
            return getPlaylists(userId);
        }
        throw new UnauthorizedException();
    }

    public PlaylistsResponseDTO createPlaylist(int playlistId, String name, int userId){
        Playlist playlistObject = new Playlist(playlistId, name, userId);

        playlistDAO.createPlaylist(playlistObject);
        return getPlaylists(userId);
    }

    public PlaylistsResponseDTO deletePlaylist(int playlistId, int userId) throws UnauthorizedException{
        if(playlistDAO.checkPlaylistOwner(userId, playlistId)){
            playlistDAO.deletePlaylist(playlistId);
            return getPlaylists(userId);
        }
        throw new UnauthorizedException();
    }
}
