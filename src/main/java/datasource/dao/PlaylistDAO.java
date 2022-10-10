package datasource.dao;

import datasource.objects.Playlist;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class PlaylistDAO extends DefaultDAO{

    private final String selectPlaylistWithId = "select * from spotitube.playlist where id = ?";
    private final String selectAllPlaylistFromUser = "select * from spotitube.playlist where ownerId = ?";
    private final String checkPlaylistOwner = "select * from spotitube.playlist where id = ? and ownerId = ?";
    private final String updateExistingPlaylist = "update spotitube.playlist set name = ? where id = ?";
    private final String createNewPlaylist = "insert into spotitube.playlist (name, owner, ownerId) values (?, ?, ?)";
    private final String deletePlaylist = "delete from spotitube.playlist where id = ?";

    private List<Playlist> playlists;
    private TrackDAO trackDAO = new TrackDAO();

    public PlaylistDAO(){
        super();
    }

    public Playlist getPlaylist(int playlistId){
        Playlist playlist = new Playlist(0, "");
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            pstmt = connection.prepareStatement(selectPlaylistWithId);
            pstmt.setInt(1, playlistId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Playlist item = new Playlist(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("ownerId")
                );
                item.setTracks(trackDAO.getTracksFromPlaylist(item.getId()));
                playlist = item;
                return playlist;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        } finally {
            closeConnections();
        }
        return playlist;
    }

    public List<Playlist> getAllPlaylistFromUser(int userId){
        playlists = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            pstmt = connection.prepareStatement(selectAllPlaylistFromUser);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Playlist item = new Playlist(
                    rs.getInt("id"),
                    rs.getString("name"),
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

    public boolean checkPlaylistOwner(int userId, int playlistId){
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            pstmt = connection.prepareStatement(checkPlaylistOwner);

            pstmt.setInt(1, playlistId);
            pstmt.setInt(2, userId);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        } finally {
            closeConnections();
        }
        return false;
    }

    public void editPlaylistName(Playlist playlist){
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            pstmt = connection.prepareStatement(updateExistingPlaylist);

            pstmt.setString(1, playlist.getName());
            pstmt.setInt(2, playlist.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        } finally {
            closeConnections();
        }
    }

    public void createPlaylist(Playlist playlist){
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            pstmt = connection.prepareStatement(createNewPlaylist);

            pstmt.setString(1, playlist.getName());
            pstmt.setBoolean(2, playlist.getOwner());
            pstmt.setInt(3, playlist.getOwnerId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        } finally {
            closeConnections();
        }
    }

    public void deletePlaylist(int playlistId){
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            pstmt = connection.prepareStatement(deletePlaylist);

            pstmt.setInt(1, playlistId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        } finally {
            closeConnections();
        }
    }
}
