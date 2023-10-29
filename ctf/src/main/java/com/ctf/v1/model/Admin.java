package com.ctf.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;


@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    @OneToMany(mappedBy = "author")
    private Set<Challenge> createdChallenges = new HashSet<>();

    @OneToMany(mappedBy = "admin")
    private Set<Competition> createdCompetitions = new HashSet<>();
    

   
}

