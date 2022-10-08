package datasource.objects;

import java.util.List;

public class Playlist {
    private int id;
    private String name;
    private boolean owner;
    private int ownerId;
    private List<Track> tracks;

    public Playlist(int id, String name, boolean owner, int ownerId) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
