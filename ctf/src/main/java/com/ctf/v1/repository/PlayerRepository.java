package com.ctf.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ctf.v1.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    
}

