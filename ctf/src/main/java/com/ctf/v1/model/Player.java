package com.ctf.v1.model;

import com.ctf.v1.token.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import jakarta.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Player implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "player")
    private List<Token> tokens;

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

    private String teamRole; // "owner" ou "member"
    private int score;

    @ManyToMany
    @JoinTable(name = "player_solved_challenge", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "challenge_id"))
    private Set<Challenge> solvedChallenges;

    @ManyToMany
    @JoinTable(name = "player_badge", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "badge_id"))
    private Set<Badge> badges = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public void setOwnerRole() {
        this.teamRole = "owner";
    }

    public void setMemberRole() {
        this.teamRole = "member";
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

}
