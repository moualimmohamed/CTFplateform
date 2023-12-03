package com.ctf.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Badge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String logoPath; 
    private LocalDateTime achievmentDate;
    private int requiredScore;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;  

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();  
    }


    
}


