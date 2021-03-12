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
    @Column(name = "ID")
    private String ID;

    @Column(name = "ownerID")
    private String ownerID;

    @Column(name = "status")
    private boolean status;

    @Column(name = "time")
    private  int time;

    @Column(name= "roomUsers")
    private List<User> roomUsers;

    /**
     * Create a new Room instance.
     * @param ID Unique identifier of the room.
     * @param ownerID The unique identifier of the owner of that room.
     * @param status Whether the room is online or not
     * @param time is how much time the room has been open
     * @param roomUsers is the list of users in the room

     */
    public Room(String ID, String ownerID, boolean status, int time, List<User> roomUsers) {
        this.ID = ID;
        this.ownerID = ownerID;
        this.status = status;
        this.time = time;
        this.roomUsers = new ArrayList<User>();
    }

    public String getID() {
        return ID;
    }

    public String getOwnerID() {
        return ownerID;
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
        return status == room.status && time == room.time && Objects.equals(ID, room.ID) && Objects.equals(ownerID, room.ownerID) && Objects.equals(roomUsers, room.roomUsers);
    }
}
