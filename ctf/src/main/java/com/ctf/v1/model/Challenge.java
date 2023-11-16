package com.ctf.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int prize;
    private String description;
    private String flag;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private File attachment;

    @ManyToMany(mappedBy = "solvedChallenges")
    private Set<Player> solvers;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Admin author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "challenges")
    private Set<Competition> competitions = new HashSet<>();


    public void addCompetition(Competition competition) {
        competitions.add(competition);
        competition.getChallenges().add(this);
    }

    public void removeCompetition(Competition competition) {
        competitions.remove(competition);
        competition.getChallenges().remove(this);
    } 
}

