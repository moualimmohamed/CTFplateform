package com.ctf.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ctf.v1.model.Admin;
import com.ctf.v1.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    @GetMapping("/{adminId}")
    public Admin getAdmin(@PathVariable Long adminId) {
        return adminService.getAdminById(adminId);
    }

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @PutMapping("/update/{adminId}")
    public Admin updateAdmin(@PathVariable Long adminId, @RequestBody Admin admin) {
        admin.setId(adminId);
        return adminService.updateAdmin(admin);
    }

    @DeleteMapping("/delete/{adminId}")
    public void deleteAdmin(@PathVariable Long adminId) {
        adminService.deleteAdmin(adminId);
    }
}

