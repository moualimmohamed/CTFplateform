package com.ctf.v1.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ctf.v1.model.Competition;

public interface CompetitionRepository extends JpaRepository<Competition, UUID> {
}
