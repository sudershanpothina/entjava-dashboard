package com.enterprise.dashboard.dao;

import com.enterprise.dashboard.model.Team;
import com.enterprise.dashboard.utils.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

/**
 * The type Test teams dao.
 */
public class TestTeamsDao {
    private GenericDao teamDao;
    private Team team;

    /**
     * Sets .
     */
    @BeforeEach
    public void setup() {
        Database database = Database.getInstance();
        database.runSQL("cleanupdb.sql");
        teamDao = new GenericDao(Team.class);
        team = new Team();
        team.setName("Team Test");
        team.setDescription("This is a test team");
    }

    /**
     * Add team.
     */
    @Test
    public void addTeam() {
        teamDao.insert(team);
        assertEquals(teamDao.getAll().size(), 4);
    }

    /**
     * Update team.
     */
    @Test
    public void updateTeam() {
        int id = teamDao.insert(team);
        team.setDescription("This is team test");
        teamDao.saveOrUpdate(team);
        assertEquals(((Team) teamDao.getById(id)).getDescription(), "This is team test");
    }

    /**
     * Delete team.
     */
    @Test
    public void deleteTeam() {
        teamDao.insert(team);
        assertEquals(teamDao.getAll().size(), 4);
        teamDao.delete(team);
        assertEquals(teamDao.getAll().size(), 3);
    }

    /**
     * Gets team by name.
     */
    @Test
    public void getTeamByName() {
        Team team = (Team) teamDao.getByProperty("name", "Avengers").get(0);
        assertEquals(team.getDescription(), "Team assigned to watch and protect east America");
    }

}
