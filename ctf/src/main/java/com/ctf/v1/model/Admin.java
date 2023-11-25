package com.ctf.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin {
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

    @OneToMany(mappedBy = "author")
    private Set<Challenge> createdChallenges = new HashSet<>();

    @OneToMany(mappedBy = "admin")
    private Set<Competition> createdCompetitions = new HashSet<>();
    

   
}

