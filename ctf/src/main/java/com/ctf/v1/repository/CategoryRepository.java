package com.ctf.v1.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ctf.v1.model.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    
}

