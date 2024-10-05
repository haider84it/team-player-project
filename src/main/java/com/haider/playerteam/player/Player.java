package com.haider.playerteam.player;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.haider.playerteam.playerprofile.PlayerProfile;
import com.haider.playerteam.team.Team;
import jakarta.persistence.*;

@Entity
@Table(name = "T_PLAYER")
public class Player {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 20)
    private String firstname;

    private String lastname;

    @Column(unique = true)
    private String email;

    private int age;

    @OneToOne(mappedBy = "player",
            cascade = CascadeType.ALL)
    private PlayerProfile playerProfile;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private Team team;

    public Player() {

    }

    public Player(String firstname, String lastname, String email, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PlayerProfile getPlayerProfile() {
        return playerProfile;
    }

    public void setPlayerProfile(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
