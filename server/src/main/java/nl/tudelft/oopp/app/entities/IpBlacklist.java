package nl.tudelft.oopp.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ipBlacklist")
public class IpBlacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "ip")
    private String ip;

    public IpBlacklist() {
    }

    public IpBlacklist(String id, String ip) {
        this.id = id;
        this.ip = ip;
    }

    /**
     * Constructor for the blacklisted IP.
     *
     * @param ip the IP.
     */
    public IpBlacklist(String ip) {
        this.ip = ip;
    }

    /**
     * Gets the blacklisted IP.
     *
     * @return the IP.
     */
    public String getIp() {
        return ip;
    }

}
