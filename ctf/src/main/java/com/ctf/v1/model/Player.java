package com.ctf.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    private String profilePicturePath;
    
    @Column(name = "registration_date", nullable = false, updatable = false)
    private LocalDateTime registrationDate; 

    @PrePersist
    protected void onCreate() {
        this.registrationDate = LocalDateTime.now();  
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

