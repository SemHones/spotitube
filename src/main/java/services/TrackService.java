package services;

import datasource.dao.TrackDAO;
import jakarta.inject.Inject;
import resources.dto.TrackResponseDTO;

public class TrackService {

    TrackDAO trackDAO = new TrackDAO();

    public TrackResponseDTO getTracks(int playlistId){
        TrackResponseDTO tracks = new TrackResponseDTO();
        tracks.setTracks(trackDAO.getTracksFromPlaylist(playlistId));
        return tracks;
    }
}
