package hac.controllers;

import hac.model.Team;
import hac.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * The TeamController class handles all HTTP requests related to Team operations in the application.
 * This includes creating a new team.
 * The class uses TeamService to perform the necessary operations related to teams.
 */

@Controller
public class TeamController {
    private final TeamService teamService;
    @Autowired
    public TeamController(TeamService teamService){
        this.teamService = teamService;
    }
    @GetMapping("/createTeam")
    public String showCreateTeamForm(Model model){
        Team team = new Team();
        model.addAttribute("team", team);
        return "create";
    }
    @PostMapping("/createTeam")
    public String createTeam(@ModelAttribute @Valid Team team, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "create";
        }
        //checks if team exists or not
        if (teamService.isTeamNameExists(team.getName())) {
            bindingResult.rejectValue("name", "error.team", "Team name already exists");
            return "create";
        }
        teamService.save(team);
        return "redirect:/";
    }
}










