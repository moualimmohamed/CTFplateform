package com.ctf.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctf.v1.model.Badge;


public interface BadgeRepository extends JpaRepository<Badge, Long> {
}