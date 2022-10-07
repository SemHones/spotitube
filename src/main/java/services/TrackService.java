package services;

import datasource.objects.Playlist;
import datasource.objects.Track;
import resources.dto.TrackResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class TrackService {

    public TrackResponseDTO getTracks(int id){
        TrackResponseDTO tracks = new TrackResponseDTO();
        if(id == 1){
            tracks.setTracks(fillHardCodedPlaylistWithHardcodedTracks());
        }
        return tracks;

    }

    public List<Track> fillHardCodedPlaylistWithHardcodedTracks(){

        List<Track> tracks = new ArrayList<Track>();
        Track track1 = new Track(1, "Burn it down", "Avenged Sevenfold", 458, "City of Evil", "2005", "Album by Avenged Sevenfold", 3);
        Track track2 = new Track(2, "The blister exists", "Slipknot", 519, "The subliminal Verses", "2004", "Album by Slipknot", 1);
        Track track3 = new Track(3, "Deutschland", "Rammstein", 522, "Rammstein", "2019", "Album by Rammstein", 4);

        tracks.add(track1);
        tracks.add(track2);
        tracks.add(track3);
        return tracks;
    }
}
