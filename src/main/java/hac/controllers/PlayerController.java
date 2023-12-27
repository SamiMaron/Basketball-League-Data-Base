package hac.controllers;

//import ch.qos.logback.core.model.Model;
import org.springframework.ui.Model;
import hac.model.Game;
import hac.model.Player;
import hac.service.GameService;
import hac.model.Team;
import hac.service.GameService;
import hac.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import hac.service.TeamService;
import java.util.Comparator;
import java.util.List;

/**
 * The PlayerController class is responsible for handling all HTTP requests related to Player operations in the application.
 * This includes listing all the players, teams, and games, and creating a new player.
 * The class uses PlayerService, TeamService, and GameService to perform necessary operations.
 */

@Controller
public class PlayerController {
    private final PlayerService playerService;
    private final GameService gameService;
    private final TeamService teamService;

    @Autowired
    public PlayerController(PlayerService playerService, TeamService teamService, GameService gameService) {
        this.playerService = playerService;
        this.teamService = teamService;
        this.gameService = gameService;
    }

    //all the info that flows to index.html
    @GetMapping("/")
    public String getPlayers(Model model) {
        List<Player> players = playerService.getAllPlayers();
        players.sort(Comparator.comparingInt(Player::getScore).reversed());
        model.addAttribute("players", players);
        List<Team> teams = teamService.findAll();
        teams.sort(Comparator.comparingInt(Team::getScore).reversed());
        model.addAttribute("teams", teams);
        List<Game> games = gameService.getAllGames();
        model.addAttribute("games", games);
        return "index";
    }

    @GetMapping("/createPlayer")
    public String createPlayerForm(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("teams", teamService.findAll());
        return "createPlayer";
    }

    @PostMapping("createPlayer")
    public String createPlayer(@ModelAttribute @Valid Player player, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "createPlayer";
        }
        playerService.save(player);
        return "redirect:/";
    }

}

