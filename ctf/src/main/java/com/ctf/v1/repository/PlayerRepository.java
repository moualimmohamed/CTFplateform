package com.ctf.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ctf.v1.model.Player;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByEmail(String email);
}
