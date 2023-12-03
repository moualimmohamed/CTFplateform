package com.ctf.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int score;
    private String joinCode; 
    private static final int MAX_MEMBERS = 5;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;  

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now(); 
    }

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @OneToMany(mappedBy = "team")
    private Set<Player> members = new HashSet<>();

    public static Team createTeam(String name, Player owner) {
        Team team = new Team();
        team.setName(name);
        team.setJoinCode(generateUniqueJoinCode());
        owner.setOwnerRole();
        owner.setTeam(team); 
        team.addMember(owner); 
        return team;
    }

    public void addMember(Player player) {
        members.add(player);
        player.setMemberRole();
        player.setTeam(this);
    }

    public void removeMember(Player player) {
        members.remove(player);
        player.setTeamRole("");
        player.setTeam(null);
    }

    private static String generateUniqueJoinCode() {
        return UUID.randomUUID().toString();
    }

    public boolean isJoinCodeValid(String code) {
        return code.equals(joinCode);
    }

    public static int getMaxMembers() {
        return MAX_MEMBERS;
    }

    public void flushScore(){
        this.score = 0;
    }
}

