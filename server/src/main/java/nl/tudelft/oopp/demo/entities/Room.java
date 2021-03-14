package nl.tudelft.oopp.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rooms")

public class Room {
    @Id
    @Column(name = "Id")
    private String Id;

    @Column(name = "ownerId")
    private String ownerId;

    @Column(name = "status")
    private boolean status;

    @Column(name = "time")
    private  int time;

    @Column(name= "roomUsers")
    private List<User> roomUsers;

    /**
     * Create a new Room instance.
     * @param Id Unique identifier of the room.
     * @param ownerId The unique identifier of the owner of that room.
     * @param status Whether the room is online or not
     * @param time is how much time the room has been open
     * @param roomUsers is the list of users in the room

     */
    public Room(String Id, String ownerId, boolean status, int time, List<User> roomUsers) {
        this.Id = Id;
        this.ownerId = ownerId;
        this.status = status;
        this.time = time;
        this.roomUsers = new ArrayList<User>();
    }

    public String getId() {
        return Id;
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
        return status == room.status && time == room.time && Objects.equals(Id, room.Id) && Objects.equals(ownerId, room.ownerId) && Objects.equals(roomUsers, room.roomUsers);
    }
}
