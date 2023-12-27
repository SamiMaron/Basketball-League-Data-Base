package hac.service;

import hac.model.Team;
import hac.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * The TeamService class provides services related to Team operations in the application.
 * This includes fetching all teams, saving a new team, and checking if a team name already exists in the database.
 * The class uses TeamRepository to perform necessary operations related to teams in the database.
 */

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public List<Team> findAll(){
        return teamRepository.findAll();
    }

    public void save(Team team) {
        teamRepository.save(team);
    }

    //this func checks if the team exists in the database already or not
    public boolean isTeamNameExists(String teamName) {
        List<Team> teams = teamRepository.findAll();
        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(teamName)) {
                return true;
            }
        }
        return false;
    }
}












