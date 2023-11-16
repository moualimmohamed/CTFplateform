package com.ctf.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctf.v1.model.PlayerSolvedChallenge;




public interface PlayerSolvedChallengeRepository extends JpaRepository<PlayerSolvedChallenge, Long> {

    boolean existsByPlayerIdAndChallengeId(Long playerId, Long challengeId);
    
}
