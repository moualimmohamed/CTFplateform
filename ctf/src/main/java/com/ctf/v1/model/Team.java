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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    private String name;
    private int score;
    private String joinCode; 

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

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
        player.setRole("");
        player.setTeam(null);
    }

    private static String generateUniqueJoinCode() {
        return UUID.randomUUID().toString();
    }

    public boolean isJoinCodeValid(String code) {
        return code.equals(joinCode);
    }
}

