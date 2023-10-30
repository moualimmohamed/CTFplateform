package com.ctf.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private int prize;
    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private File attachment;

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

