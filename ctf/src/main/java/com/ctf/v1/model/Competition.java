package com.ctf.v1.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String joinCode; 

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "competition")
    private Set<Team> teams = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "competition_challenges",
        joinColumns = @JoinColumn(name = "competition_id"),
        inverseJoinColumns = @JoinColumn(name = "challenge_id")
    )
    private Set<Challenge> challenges = new HashSet<>();

    
    public static Competition createCompetition(String name, Admin owner) {
        Competition competition = new Competition();
        competition.setName(name);
        competition.setJoinCode(generateUniqueJoinCode());
        owner.getCreatedCompetitions().add(competition);
        return competition;
    }

    public void addTeam(Team team) {
        teams.add(team);
        team.setCompetition(this);
    }

    public void removeTeam(Team team) {
        teams.remove(team);
        team.setCompetition(null);
    }

    public void addChallenge(Challenge challenge) {
        challenges.add(challenge);
        challenge.getCompetitions().add(this);
    }

    public void removeChallenge(Challenge challenge) {
        challenges.remove(challenge);
        challenge.getCompetitions().remove(this);
    }

    private static String generateUniqueJoinCode() {
        return UUID.randomUUID().toString();
    }
}


