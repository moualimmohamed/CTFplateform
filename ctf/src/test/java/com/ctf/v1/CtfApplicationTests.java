package com.ctf.v1;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import com.ctf.v1.webServices.CompetitionChangeStatusService;
import com.ctf.v1.exceptions.CompetitionStatusChangeException;
import com.ctf.v1.exceptions.RemoveChallengeCompetitionException;
import com.ctf.v1.model.Competition;

import com.ctf.v1.exceptions.AddChallengeCompetitionException;
import com.ctf.v1.exceptions.CompetitionQuitException;
import com.ctf.v1.model.Challenge;
import com.ctf.v1.service.AdminService;
import com.ctf.v1.service.ChallengeService;
import com.ctf.v1.service.CompetitionService;
import com.ctf.v1.service.PlayerService;
import com.ctf.v1.service.PlayerSolvedChallengeService;
import com.ctf.v1.service.TeamService;
import com.ctf.v1.webServices.AddChallengeCompetitionService;
import com.ctf.v1.webServices.CompetitionCreationService;
import com.ctf.v1.exceptions.ServiceException;
import com.ctf.v1.exceptions.TeamCreationException;
import com.ctf.v1.webServices.CompetitionJoinService;
import com.ctf.v1.webServices.CompetitionQuitService;
import com.ctf.v1.webServices.FlagCheckingService;
import com.ctf.v1.webServices.RemoveChallengeCompetitionService;
import com.ctf.v1.webServices.ScoringService;
import com.ctf.v1.webServices.SolvedChallengeService;
import com.ctf.v1.webServices.TeamCreationService;
import com.ctf.v1.webServices.TeamJoinService;
import com.ctf.v1.model.Player;
import com.ctf.v1.model.PlayerSolvedChallenge;
import com.ctf.v1.model.Team;
import org.junit.jupiter.api.BeforeEach;
import com.ctf.v1.model.Admin;

@SpringBootTest
class CtfApplicationTests {
	@Mock
	private Competition mockCompetition;

	@Mock
	private CompetitionService competitionService;

	@Mock
	private Competition competition;

	@Mock
	private ChallengeService challengeService;

	@Mock
	private AdminService adminService;

	@Mock
	private PlayerService playerService;

	@Mock
	private TeamService teamService;

	@Mock
	private PlayerSolvedChallengeService playerSolvedChallengeService;

	@InjectMocks
	private TeamJoinService teamJoinService;

	@InjectMocks
	private SolvedChallengeService solvedChallengeService;

	@InjectMocks
	private ScoringService scoringService;

	@InjectMocks
	private CompetitionQuitService competitionQuitService;

	@InjectMocks
	private CompetitionJoinService competitionJoinService;

	@InjectMocks
	private AddChallengeCompetitionService addChallengeCompetitionService;

	@InjectMocks
	private CompetitionChangeStatusService competitionChangeStatusService;

	@InjectMocks
	private CompetitionCreationService competitionCreationService;

	@InjectMocks
	private FlagCheckingService flagCheckingService;

	@InjectMocks
	private TeamCreationService teamCreationService;

	@InjectMocks
	private RemoveChallengeCompetitionService removeChallengeCompetitionService;

	private final Long playerId = 1L;
	private final Long competitionId = 1L;
	private final String joinCode = "VALID_CODE";

	@BeforeEach
	void setUpCompetitionJoinService() {
		Player mockPlayer = new Player();
		Team mockTeam = new Team();

		// Configuring the mock player
		mockPlayer.setTeam(mockTeam);
		mockPlayer.setTeamRole("owner");

		// Configuring the mock competition
		mockCompetition.setStatus("open");
		mockCompetition.setTeams(new HashSet<>());
		when(mockCompetition.isJoinCodeValid(joinCode)).thenReturn(true);

		// Define behavior for the mocked services
		when(playerService.getPlayerById(playerId)).thenReturn(mockPlayer);
		when(teamService.getTeamById(mockTeam.getId())).thenReturn(mockTeam);
		when(competitionService.getCompetitionById(competitionId)).thenReturn(mockCompetition);
	}

	@Test
	void contextLoads() {
	}

	@Test
	void testAddChallenge_Success() {
		Long challengeId = 1L;
		Long competitionId = 1L;
		Challenge challenge = new Challenge();
		Competition competition = new Competition();

		when(challengeService.getChallengeById(challengeId)).thenReturn(challenge);
		when(competitionService.getCompetitionById(competitionId)).thenReturn(competition);

		assertDoesNotThrow(() -> addChallengeCompetitionService.addChallenge(challengeId, competitionId));

		verify(competitionService).updateCompetition(competition);
		verify(challengeService).updateChallenge(challenge);
	}

	@Test
	void testAddChallenge_ChallengeOrCompetitionNotFound() {
		Long challengeId = 1L;
		Long competitionId = 1L;

		when(challengeService.getChallengeById(challengeId)).thenReturn(null);
		when(competitionService.getCompetitionById(competitionId)).thenReturn(null);

		assertThrows(AddChallengeCompetitionException.class,
				() -> addChallengeCompetitionService.addChallenge(challengeId, competitionId));
	}

	@Test
	void testAddChallenge_ChallengeAlreadyAdded() {
		Long challengeId = 1L;
		Long competitionId = 1L;
		Challenge challenge = new Challenge();
		Competition competition = new Competition();
		competition.getChallenges().add(challenge);

		when(challengeService.getChallengeById(challengeId)).thenReturn(challenge);
		when(competitionService.getCompetitionById(competitionId)).thenReturn(competition);

		assertThrows(AddChallengeCompetitionException.class,
				() -> addChallengeCompetitionService.addChallenge(challengeId, competitionId));
	}

	@Test
	void testCompetitionChangeStatus_Success_OpenToClosed() {
		Long competitionId = 1L;
		Competition competition = new Competition();
		competition.setStatus("open");

		when(competitionService.getCompetitionById(competitionId)).thenReturn(competition);
		when(competitionService.updateCompetition(competition)).thenReturn(competition);

		Competition updatedCompetition = competitionChangeStatusService.competitionChangeStatus(competitionId);

		assertEquals("closed", updatedCompetition.getStatus());
		verify(competitionService).updateCompetition(competition);
	}

	@Test
	void testCompetitionChangeStatus_Success_ClosedToOpen() {
		Long competitionId = 1L;
		Competition competition = new Competition();
		competition.setStatus("closed");

		when(competitionService.getCompetitionById(competitionId)).thenReturn(competition);
		when(competitionService.updateCompetition(competition)).thenReturn(competition);

		Competition updatedCompetition = competitionChangeStatusService.competitionChangeStatus(competitionId);

		assertEquals("open", updatedCompetition.getStatus());
		verify(competitionService).updateCompetition(competition);
	}

	@Test
	void testCompetitionChangeStatus_CompetitionNotFound() {
		Long competitionId = 1L;

		when(competitionService.getCompetitionById(competitionId)).thenReturn(null);

		assertThrows(CompetitionStatusChangeException.class,
				() -> competitionChangeStatusService.competitionChangeStatus(competitionId));
	}

	@Test
	void testCreateCompetition_Success() throws ServiceException {
		String competitionName = "New Competition";
		Long authorId = 1L;
		Admin author = new Admin();
		Competition competition = new Competition();

		when(adminService.getAdminById(authorId)).thenReturn(author);
		when(competitionService.isCompetitionNameExists(competitionName)).thenReturn(false);
		when(competitionService.createCompetition(any(Competition.class))).thenReturn(competition);

		Competition createdCompetition = competitionCreationService.createCompetition(competitionName, authorId);

		assertNotNull(createdCompetition);
		verify(adminService).getAdminById(authorId);
		verify(competitionService).isCompetitionNameExists(competitionName);
		verify(competitionService).createCompetition(any(Competition.class));
	}

	@Test
	void testCreateCompetition_AdminNotFound() {
		String competitionName = "New Competition";
		Long authorId = 1L;

		when(adminService.getAdminById(authorId)).thenReturn(null);

		assertThrows(ServiceException.class,
				() -> competitionCreationService.createCompetition(competitionName, authorId));
	}

	@Test
	void testCreateCompetition_CompetitionNameExists() {
		String competitionName = "Existing Competition";
		Long authorId = 1L;
		Admin author = new Admin();

		when(adminService.getAdminById(authorId)).thenReturn(author);
		when(competitionService.isCompetitionNameExists(competitionName)).thenReturn(true);

		assertThrows(ServiceException.class,
				() -> competitionCreationService.createCompetition(competitionName, authorId));
	}

	@Test
	void testQuitCompetition_Success() throws CompetitionQuitException {
		Long playerId = 1L;
		Long teamId = 1L;
		Long competitionId = 1L;
		Player mockPlayer = new Player();
		Team mockTeam = new Team();
		Competition mockCompetition = new Competition();

		mockPlayer.setTeam(mockTeam);
		mockPlayer.setTeamRole("owner");
		mockTeam.setCompetition(mockCompetition);

		when(playerService.getPlayerById(playerId)).thenReturn(mockPlayer);
		when(teamService.getTeamById(teamId)).thenReturn(mockTeam);
		when(competitionService.getCompetitionById(competitionId)).thenReturn(mockCompetition);

		assertDoesNotThrow(() -> competitionQuitService.quitCompetition(playerId, teamId, competitionId));

		verify(teamService).updateTeam(mockTeam);
		verify(competitionService).updateCompetition(mockCompetition);
	}

	@Test
	void testQuitCompetition_EntityNotFound() {
		Long playerId = 1L;
		Long teamId = 1L;
		Long competitionId = 1L;

		when(playerService.getPlayerById(playerId)).thenReturn(null);
		when(teamService.getTeamById(teamId)).thenReturn(null);
		when(competitionService.getCompetitionById(competitionId)).thenReturn(null);

		assertThrows(CompetitionQuitException.class,
				() -> competitionQuitService.quitCompetition(playerId, teamId, competitionId));
	}

	@Test
	void testQuitCompetition_NotTeamOwner() {
		Long playerId = 1L;
		Long teamId = 1L;
		Long competitionId = 1L;
		Player mockPlayer = new Player();
		Team mockTeam = new Team();
		mockPlayer.setTeam(mockTeam);
		mockPlayer.setTeamRole("member"); // Not the owner

		when(playerService.getPlayerById(playerId)).thenReturn(mockPlayer);
		when(teamService.getTeamById(teamId)).thenReturn(mockTeam);

		assertThrows(CompetitionQuitException.class,
				() -> competitionQuitService.quitCompetition(playerId, teamId, competitionId));
	}

	@Test
	void testQuitCompetition_TeamNotInCompetition() {
		Long playerId = 1L;
		Long teamId = 1L;
		Long competitionId = 1L;
		Player mockPlayer = new Player();
		Team mockTeam = new Team();
		Competition mockCompetition = new Competition();
		Competition differentCompetition = new Competition();

		// Ensure mockCompetition and differentCompetition are distinct
		mockCompetition.setId(competitionId); // Assuming an ID setter method exists
		differentCompetition.setId(competitionId + 1); // Different ID

		mockPlayer.setTeam(mockTeam);
		mockPlayer.setTeamRole("owner");
		mockTeam.setCompetition(differentCompetition); // Team is part of a different competition

		when(playerService.getPlayerById(playerId)).thenReturn(mockPlayer);
		when(teamService.getTeamById(teamId)).thenReturn(mockTeam);
		when(competitionService.getCompetitionById(competitionId)).thenReturn(mockCompetition);

		assertThrows(CompetitionQuitException.class,
				() -> competitionQuitService.quitCompetition(playerId, teamId, competitionId));
	}

	@Test
	void testRemoveChallenge_Success() {
		Long challengeId = 1L;
		Long competitionId = 1L;
		Challenge mockChallenge = new Challenge();
		Competition mockCompetition = new Competition();
		mockCompetition.getChallenges().add(mockChallenge);

		when(challengeService.getChallengeById(challengeId)).thenReturn(mockChallenge);
		when(competitionService.getCompetitionById(competitionId)).thenReturn(mockCompetition);

		assertDoesNotThrow(() -> removeChallengeCompetitionService.removeChallenge(challengeId, competitionId));
		assertFalse(mockCompetition.getChallenges().contains(mockChallenge));
	}

	@Test
	void testRemoveChallenge_ChallengeOrCompetitionNotFound() {
		Long challengeId = 1L;
		Long competitionId = 1L;

		when(challengeService.getChallengeById(challengeId)).thenReturn(null);
		when(competitionService.getCompetitionById(competitionId)).thenReturn(null);

		assertThrows(RemoveChallengeCompetitionException.class,
				() -> removeChallengeCompetitionService.removeChallenge(challengeId, competitionId));
	}

	@Test
	void testRemoveChallenge_ChallengeNotInCompetition() {
		Long challengeId = 1L;
		Long competitionId = 1L;
		Challenge mockChallenge = new Challenge();
		Competition mockCompetition = new Competition();

		when(challengeService.getChallengeById(challengeId)).thenReturn(mockChallenge);
		when(competitionService.getCompetitionById(competitionId)).thenReturn(mockCompetition);

		assertThrows(RemoveChallengeCompetitionException.class,
				() -> removeChallengeCompetitionService.removeChallenge(challengeId, competitionId));
	}

	@Test
	void testUpdateScore_Success() {
		Long playerId = 1L;
		Long challengeId = 1L;
		int initialPlayerScore = 100;
		int initialTeamScore = 500;
		int prize = 50;

		Player mockPlayer = new Player();
		mockPlayer.setScore(initialPlayerScore);
		Team mockTeam = new Team();
		mockTeam.setScore(initialTeamScore);
		mockPlayer.setTeam(mockTeam);

		Challenge mockChallenge = new Challenge();
		mockChallenge.setPrize(prize);

		when(playerService.getPlayerById(playerId)).thenReturn(mockPlayer);
		when(challengeService.getChallengeById(challengeId)).thenReturn(mockChallenge);

		scoringService.updateScore(playerId, challengeId);

		assertEquals(initialPlayerScore + prize, mockPlayer.getScore());
		assertEquals(initialTeamScore + prize, mockTeam.getScore());
		verify(playerService).updatePlayer(mockPlayer);
		verify(teamService).updateTeam(mockTeam);
	}

	@Test
	void testUpdateScore_PlayerOrChallengeNotFound() {
		Long playerId = 1L;
		Long challengeId = 1L;

		when(playerService.getPlayerById(playerId)).thenReturn(null);
		when(challengeService.getChallengeById(challengeId)).thenReturn(null);

		assertThrows(ServiceException.class, () -> scoringService.updateScore(playerId, challengeId));
	}

	@Test
	void testUpdateScore_PlayerNotInTeam() {
		Long playerId = 1L;
		Long challengeId = 1L;
		Player mockPlayer = new Player();
		Challenge mockChallenge = new Challenge();

		mockPlayer.setTeam(null); // Player is not in any team

		when(playerService.getPlayerById(playerId)).thenReturn(mockPlayer);
		when(challengeService.getChallengeById(challengeId)).thenReturn(mockChallenge);

		assertThrows(ServiceException.class, () -> scoringService.updateScore(playerId, challengeId));
	}

	@Test
	void testMarkChallengeAsSolved_Success() {
		Long playerId = 1L;
		Long challengeId = 1L;
		Player mockPlayer = new Player();
		mockPlayer.setTeam(new Team()); // Assuming a team is set for the player

		when(playerService.getPlayerById(playerId)).thenReturn(mockPlayer);
		when(challengeService.getChallengeById(challengeId)).thenReturn(new Challenge());
		when(playerSolvedChallengeService.hasPlayerSolvedChallenge(playerId, challengeId)).thenReturn(false);
		when(playerSolvedChallengeService.hasPlayerSolvedChallenge(anyLong(), eq(challengeId))).thenReturn(false);

		assertTrue(solvedChallengeService.markChallengeAsSolved(playerId, challengeId));
		verify(playerSolvedChallengeService).createPlayerSolvedChallenge(any(PlayerSolvedChallenge.class));
	}

	@Test
	void testMarkChallengeAsSolved_AlreadySolvedByPlayer() {
		Long playerId = 1L;
		Long challengeId = 1L;

		when(playerSolvedChallengeService.hasPlayerSolvedChallenge(playerId, challengeId)).thenReturn(true);

		assertFalse(solvedChallengeService.markChallengeAsSolved(playerId, challengeId));
	}

	@Test
	void testMarkChallengeAsSolved_AlreadySolvedByTeam() {
		Long playerId = 1L;
		Long challengeId = 1L;
		Player mockPlayer = new Player();
		Team mockTeam = new Team();
		Set<Player> teamMembers = new HashSet<>();
		teamMembers.add(mockPlayer); // Add mockPlayer to the team
		mockTeam.setMembers(teamMembers);
		mockPlayer.setTeam(mockTeam);

		when(playerService.getPlayerById(playerId)).thenReturn(mockPlayer);
		when(playerSolvedChallengeService.hasPlayerSolvedChallenge(anyLong(), eq(challengeId))).thenReturn(true);

		assertFalse(solvedChallengeService.markChallengeAsSolved(playerId, challengeId));
	}

	@Test
	void testCreateTeam_Success() {
		String teamName = "NewTeam";
		Long ownerId = 1L;

		Player owner = new Player();
		when(playerService.getPlayerById(ownerId)).thenReturn(owner);
		when(teamService.isTeamNameExists(teamName)).thenReturn(false);
		when(teamService.createTeam(any(Team.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Team createdTeam = teamCreationService.createTeam(teamName, ownerId);

		assertNotNull(createdTeam);
		assertEquals(teamName, createdTeam.getName());

	}

	@Test
	void testCreateTeam_TeamNameExists() {
		String teamName = "ExistingTeam";
		Long ownerId = 1L;

		when(teamService.isTeamNameExists(teamName)).thenReturn(true);

		assertThrows(TeamCreationException.class, () -> teamCreationService.createTeam(teamName, ownerId));
	}

	@Test
	void testCreateTeam_PlayerAlreadyInTeam() {
		String teamName = "NewTeam";
		Long ownerId = 1L;

		Player owner = new Player();
		Team existingTeam = new Team();
		owner.setTeam(existingTeam);

		when(playerService.getPlayerById(ownerId)).thenReturn(owner);

		assertThrows(TeamCreationException.class, () -> teamCreationService.createTeam(teamName, ownerId));
	}

	////////////////////////////

}
