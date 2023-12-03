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
public class Admin implements Serializable {
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

    @OneToMany(mappedBy = "author")
    private Set<Challenge> createdChallenges = new HashSet<>();

    @OneToMany(mappedBy = "admin")
    private Set<Competition> createdCompetitions = new HashSet<>();
    

   
}

