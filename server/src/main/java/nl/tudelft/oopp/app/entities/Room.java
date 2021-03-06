package nl.tudelft.oopp.app.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "rooms")

public class Room {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "ownerId")
    private String ownerId;

    @Column(name = "status")
    private boolean status;

    @Column(name = "time")
    private int time;

    @OneToMany
    private List<User> roomUsers;

    /**
     * Create a new Room instance.
     *
     * @param id      Unique identifier of the room.
     * @param ownerId The unique identifier of the owner of that room.
     * @param status  Whether the room is online or not
     * @param time    is how much time the room has been open
     */
    public Room(String id, String ownerId, boolean status, int time) {
        this.id = id;
        this.ownerId = ownerId;
        this.status = status;
        this.time = time;
        this.roomUsers = new ArrayList<User>();
    }

    public Room() {

    }

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTime() {
        return time;
    }

    public List<User> getRoomUsers() {
        return roomUsers;
    }

    public void addUser(User user) {
        roomUsers.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return status == room.status
                && time == room.time
                && Objects.equals(id, room.id)
                && Objects.equals(ownerId, room.ownerId)
                && Objects.equals(roomUsers, room.roomUsers);
    }

}
