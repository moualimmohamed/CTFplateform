package com.ctf.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctf.v1.model.PlayerSolvedChallenge;


import java.util.UUID;

public interface PlayerSolvedChallengeRepository extends JpaRepository<PlayerSolvedChallenge, UUID> {
    
}
