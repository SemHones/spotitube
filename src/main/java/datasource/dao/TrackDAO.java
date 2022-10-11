package datasource.dao;

import datasource.objects.Track;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class TrackDAO extends DefaultDAO{

    private final String selectTrackById = "select * from spotitube.track where id = ?";
    private final String selectTracksFromPlaylist = "select t.* from spotitube.track t inner join spotitube.track_on_playlist tp on t.id = tp.trackId where tp.playlistId = ?";
    private final String selectTracksOutsidePlaylist = "select * from spotitube.track where id not in (select t.id from spotitube.track t inner join spotitube.track_on_playlist tp on t.id = tp.trackId where tp.playlistId = ?)";
    private final String addTrackToPlaylist = "insert into spotitube.track_on_playlist (playlistId, trackId) values (?, ?)";
    private final String updateTrackOfflineAvailable = "update spotitube.track set offlineAvailable = ? where id = ?";
    private final String deleteTrackFromPlaylist = "delete from spotitube.track_on_playlist where playlistId = ? and trackId = ?";

    private List<Track> tracks;

    public TrackDAO(){
        super();
    }

    public Track getTrack(int trackId) {
        Track emptyTrack = new Track(0, "","","","","", 0, 0, false);
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            pstmt = connection.prepareStatement(selectTrackById);

            pstmt.setInt(1, trackId);

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
                return item;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        } finally {
            closeConnections();
        }
        return emptyTrack;
    }

    public List<Track> getTracks(int playlistId, boolean insidePlaylist) {
        tracks = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            if(insidePlaylist){
                pstmt = connection.prepareStatement(selectTracksFromPlaylist);
            }
            else{
                pstmt = connection.prepareStatement(selectTracksOutsidePlaylist);
            }

            pstmt.setInt(1, playlistId);

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


    public void addTrackToPlaylist(int playlistId, int trackId){
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            pstmt = connection.prepareStatement(addTrackToPlaylist);

            pstmt.setInt(1, playlistId);
            pstmt.setInt(2, trackId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        } finally {
            closeConnections();
        }
    }

    public void setTrackOfflineAvailable(boolean offlineAvailable, int trackId){
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            pstmt = connection.prepareStatement(updateTrackOfflineAvailable);

            pstmt.setBoolean(1, offlineAvailable);
            pstmt.setInt(2, trackId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        } finally {
            closeConnections();
        }
    }

    public void deleteTrack(int playlistId, int trackId){
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            pstmt = connection.prepareStatement(deleteTrackFromPlaylist);

            pstmt.setInt(1, playlistId);
            pstmt.setInt(2, trackId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        } finally {
            closeConnections();
        }
    }
}
