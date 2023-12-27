package hac.service;

import hac.model.Game;
import hac.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * The GameService class provides services related to Game operations in the application.
 * This includes fetching all games and saving a new game.
 * The class uses GameRepository to perform necessary operations related to games in the database.
 */

@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public void saveGame(Game game) {
        gameRepository.save(game);
    }
}
















