package com.ctf.v1.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ctf.v1.model.Badge;
import com.ctf.v1.repository.BadgeRepository;

import java.util.List;
import java.util.UUID;

@Service
public class BadgeService {
    @Autowired
    private BadgeRepository badgeRepository;

    public List<Badge> getAllBadges() {
        return badgeRepository.findAll();
    }

    public Badge getBadgeById(UUID id) {
        return badgeRepository.findById(id).orElse(null);
    }

    public Badge createBadge(Badge badge) {
        return badgeRepository.save(badge);
    }

    public void deleteBadge(UUID id) {
        badgeRepository.deleteById(id);
    }

}

