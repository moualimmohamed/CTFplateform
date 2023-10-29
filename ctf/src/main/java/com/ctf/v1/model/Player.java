package com.ctf.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Player extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    private String role; // "owner" ou "member"
    private int score;
    
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

