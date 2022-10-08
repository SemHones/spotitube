package datasource.dao;

import datasource.objects.Track;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class TrackDAO extends DefaultDAO{

    private String selectTrackFromPlaylist = "select t.* from spotitube.track t inner join spotitube.track_on_playlist tp on t.id = tp.trackId";

    private List<Track> tracks;

    public TrackDAO(){
        super();
    }

    public List<Track> getTracksFromPlaylist(int playlistId) {
        tracks = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            pstmt = connection.prepareStatement(selectTrackFromPlaylist + " where tp.playlistId = " + playlistId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Track item = new Track(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("performer"),
                    rs.getString("album"),
                    rs.getString("publicationDate"),
                    rs.getString("description"),
                    rs.getInt("duration"),
                    rs.getInt("playCount"),
                    rs.getBoolean("offlineAvailable")
                );
                tracks.add(item);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        } finally {
            closeConnections();
        }
        return tracks;
    }
}
