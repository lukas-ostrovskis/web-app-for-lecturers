package nl.tudelft.oopp.app.data;


import com.fasterxml.jackson.annotation.JsonCreator;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class User {

    private String id;
    private String name;
    private String email;
    private String role;
    private String ip;

    /**
     * Constructor used when initially creating client-side user
     * @param email of user
     * @param role of user
     */
    public User(String email, String role, String name) {
        this.email = email;
        this.role = role;
        this.name = name;
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor used by the JSON Creator to construct a new user.
     *
     * @param id Unique identifier as to be used in the database.
     * @param name Actual name of the user.
     * @param email Email of the user.
     * @param role Role of the user (lecturer, student, moderator).
     * @param ip IP address of the user.
     */
    @JsonCreator
    public User(String id, String name, String email, String role, String ip) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.ip = ip;
    }

    public String getId() { return id; }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() { return role; }

    public String getIp() {
        return ip;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                name.equals(user.name) &&
                email.equals(user.email) &&
                role.equals(user.role) &&
                ip.equals(user.ip);
    }

}