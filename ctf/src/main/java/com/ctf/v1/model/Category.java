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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @OneToMany(mappedBy = "category")
    private Set<Challenge> challenges = new HashSet<>();

   
}

