package datasource.dao;

import datasource.objects.Playlist;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class PlaylistDAO extends DefaultDAO{

    private String selectAllPlaylistFromUser = "select * from spotitube.playlist";

    private List<Playlist> playlists;
    private TrackDAO trackDAO = new TrackDAO();

    public PlaylistDAO(){
        super();
    }

    public List<Playlist> getAllPlaylistFromUser(int userId){
        playlists = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            pstmt = connection.prepareStatement(selectAllPlaylistFromUser + " where ownerId = " + userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Playlist item = new Playlist(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBoolean("owner"),
                    rs.getInt("ownerId")
                );
                item.setTracks(trackDAO.getTracksFromPlaylist(item.getId()));
                playlists.add(item);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        } finally {
            closeConnections();
        }
        return playlists;
    }
}
