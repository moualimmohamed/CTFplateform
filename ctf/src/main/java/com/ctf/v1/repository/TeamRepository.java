package com.ctf.v1.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ctf.v1.model.Team;

public interface TeamRepository extends JpaRepository<Team, UUID> {
    
}

