package services;

import datasource.dao.TrackDAO;
import jakarta.ws.rs.NotFoundException;
import resources.dto.TrackRequestDTO;
import resources.dto.TrackResponseDTO;

public class TrackService {

    TrackDAO trackDAO = new TrackDAO();

    public TrackResponseDTO getTracksFromPlaylist(int playlistId){
        TrackResponseDTO tracks = new TrackResponseDTO();
        boolean insidePlaylist = true;

        tracks.setTracks(trackDAO.getTracks(playlistId, insidePlaylist));
        return tracks;
    }

    public TrackResponseDTO getTracksOutsidePlaylist(int playlistId){
        TrackResponseDTO tracks = new TrackResponseDTO();
        boolean insidePlaylist = false;

        tracks.setTracks(trackDAO.getTracks(playlistId, insidePlaylist));
        return tracks;
    }

    public TrackResponseDTO addTrack(int playlistId, TrackRequestDTO track) throws NotFoundException {
        TrackResponseDTO tracks = new TrackResponseDTO();
        boolean insidePlaylist = true;

        if(!trackDAO.getTrack(track.getId()).getTitle().isEmpty()){
            trackDAO.addTrackToPlaylist(playlistId, track.getId());
            trackDAO.setTrackOfflineAvailable(track.isOfflineAvailable(), track.getId());
            tracks.setTracks(trackDAO.getTracks(playlistId, insidePlaylist));
            return tracks;
        }
        throw new NotFoundException();
    }

    public TrackResponseDTO deleteTrack(int playlistId, int trackId) throws NotFoundException {
        TrackResponseDTO tracks = new TrackResponseDTO();
        boolean insidePlaylist = true;

        if(!trackDAO.getTrack(trackId).getTitle().isEmpty()){
            trackDAO.deleteTrack(playlistId, trackId);
            tracks.setTracks(trackDAO.getTracks(playlistId, insidePlaylist));
            return tracks;
        }
        throw new NotFoundException();
    }
}
