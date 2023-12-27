package hac.controllers;

import hac.model.Game;
import hac.model.Player;
import hac.service.GameService;
import hac.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * The GameController class handles all HTTP requests related to Game operations in the application.
 * This includes creating a new game.
 * The class uses GameService and TeamService to perform the necessary operations related to games and teams.
 */

@Controller
public class GameController {
    private final GameService gameService;
    private final TeamService teamService;

    @Autowired
    public GameController(GameService gameService, TeamService teamService) {
        this.gameService = gameService;
        this.teamService = teamService;
    }

    @GetMapping("/createGame")
    public String showCreateGameForm(Model model) {
        Game game = new Game();
        model.addAttribute("game", game);
        model.addAttribute("teams", teamService.findAll()); // gets created teams
        return "createGame";
    }

    @PostMapping("/createGame")
    public String saveGame(@ModelAttribute("game") Game game, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "createGame";
        }
        //validating home and away team are not equal
        if (game.getHomeTeam().getId().equals(game.getAwayTeam().getId())){
            bindingResult.rejectValue("awayTeam", "error.game", "Away team name cannot be the same as the home team name");
            model.addAttribute("teams", teamService.findAll());
            return "createGame";
        }
        gameService.saveGame(game);
        return "redirect:/";
    }
}
