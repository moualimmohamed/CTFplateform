package com.ctf.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;


@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Player extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String role; // "owner" ou "member"
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
        this.role = "owner";
    }

   
    public void setMemberRole() {
        this.role = "member";
    }

    
}

