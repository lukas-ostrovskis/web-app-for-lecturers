package nl.tudelft.oopp.demo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    private  int time;

    @ManyToMany
    private List<User> roomUsers;

    /**
     * Create a new Room instance.
     * @param id Unique identifier of the room.
     * @param ownerId The unique identifier of the owner of that room.
     * @param status Whether the room is online or not
     * @param time is how much time the room has been open
     * @param roomUsers is the list of users in the room

     */
    public Room(String id, String ownerId, boolean status, int time, List<User> roomUsers) {
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

    public int getTime() {
        return time;
    }

    public List<User> getRoomUsers() {
        return roomUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return status == room.status && time == room.time && Objects.equals(id, room.id) && Objects.equals(ownerId, room.ownerId) && Objects.equals(roomUsers, room.roomUsers);
    }
}
