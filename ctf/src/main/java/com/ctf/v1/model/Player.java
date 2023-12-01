package com.ctf.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private File profilePicture;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registration_date", nullable = false, updatable = false)
    private Date registrationDate;

    @PrePersist
    protected void onCreate() {
        this.registrationDate = new Date();
    }
    
    private String teamRole; // "owner" ou "member"
    private int score;

    @ManyToMany
    @JoinTable(
    name = "player_solved_challenge",
    joinColumns = @JoinColumn(name = "player_id"),
    inverseJoinColumns = @JoinColumn(name = "challenge_id")
    )
    private Set<Challenge> solvedChallenges;


    @ManyToMany
    @JoinTable( name = "player_badge",
        joinColumns = @JoinColumn(name = "player_id"),
        inverseJoinColumns = @JoinColumn(name = "badge_id"))
    private Set<Badge> badges = new HashSet<>();
    
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public void setOwnerRole() {
        this.teamRole = "owner";
    }

   
    public void setMemberRole() {
        this.teamRole = "member";
    }

    public void flushScore(){
        this.score = 0;
    }

    
}

