package com.ctf.v1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ctf.v1.model.Badge;
import com.ctf.v1.service.BadgeService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/badges")
public class BadgeController {
    @Autowired
    private BadgeService badgeService;

    @GetMapping
    public List<Badge> getAllBadges() {
        return badgeService.getAllBadges();
    }

    @GetMapping("/{id}")
    public Badge getBadgeById(@PathVariable UUID id) {
        return badgeService.getBadgeById(id);
    }

    @PostMapping
    public Badge createBadge(@RequestBody Badge badge) {
        return badgeService.createBadge(badge);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBadge(@PathVariable UUID id) {
        badgeService.deleteBadge(id);
    }

}

