package nl.tudelft.oopp.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    @Column(name = "ip")
    private String ip;

    /**
     * Instantiates a new User.
     */
    public User() {

    }
    /**
     * Create a new User instance.
     *
     * @param id    Unique identifier as to be used in the database.
     * @param name  Actual name of the user.
     * @param email Email of the user.
     * @param role  Role of the user (lecturer, student, moderator).
     * @param ip    IP address of the user.
     */
    public User(String id, String name, String email, String role, String ip) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.ip = ip;
    }

    public User(String name, String email, String role, String ip) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.ip = ip;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getIp() {
        return ip;
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
