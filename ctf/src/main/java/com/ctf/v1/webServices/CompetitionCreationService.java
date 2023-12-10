package com.ctf.v1.webServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctf.v1.exceptions.ServiceException;
import com.ctf.v1.model.Admin;
import com.ctf.v1.model.Competition;
import com.ctf.v1.service.AdminService;
import com.ctf.v1.service.CompetitionService;

import jakarta.transaction.Transactional;

@Service
public class CompetitionCreationService {

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private AdminService adminService;

    @Transactional
    public Competition createCompetition(String competitionName, Long authorId) throws ServiceException {

        try {
            Admin author = adminService.getAdminById(authorId);

            if (author == null) {
                throw new ServiceException("Admin not found with ID: " + authorId);
            }

            if (competitionService.isCompetitionNameExists(competitionName)) {
                throw new ServiceException("Competition name is already in use");
            }

            Competition newCompetition = Competition.createCompetition(competitionName, author);

            try {
                return competitionService.createCompetition(newCompetition);
            } catch (Exception e) {
                throw new ServiceException("Error creating competition"+ e.getMessage());
            }
        } catch (Exception e) {
            throw new ServiceException("Error fetching admin" + e.getMessage());
        }
    }
}
