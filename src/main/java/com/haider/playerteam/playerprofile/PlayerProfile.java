package com.haider.playerteam.playerprofile;

import com.haider.playerteam.player.Player;
import jakarta.persistence.*;

@Entity
public class PlayerProfile {

    @Id
    @GeneratedValue
    private Integer id;

    private String bio;

    @OneToOne
    @JoinColumn(
           name = "student_id"
    )
    private Player player;

    public PlayerProfile() {}

    public PlayerProfile(String bio) {
        this.bio = bio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
