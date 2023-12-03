package com.ctf.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;  

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();  
    }

    @OneToMany(mappedBy = "category")
    private Set<Challenge> challenges = new HashSet<>();

   
}

