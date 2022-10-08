package services;

import datasource.dao.PlaylistDAO;
import resources.dto.PlaylistsResponseDTO;

public class PlaylistService {

    private PlaylistDAO playlistDAO = new PlaylistDAO();

    public PlaylistsResponseDTO getPlaylists(int userId){
        PlaylistsResponseDTO playlistsResponseDTO = new PlaylistsResponseDTO();

        playlistsResponseDTO.setPlaylists(playlistDAO.getAllPlaylistFromUser(userId));
        return playlistsResponseDTO;
    }
}
