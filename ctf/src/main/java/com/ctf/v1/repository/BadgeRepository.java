package com.ctf.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctf.v1.model.Badge;
import java.util.UUID;

public interface BadgeRepository extends JpaRepository<Badge, UUID> {
}