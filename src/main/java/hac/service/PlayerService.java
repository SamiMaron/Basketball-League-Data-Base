package hac.service;

import hac.model.Player;
import hac.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The PlayerService class provides services related to Player operations in the application.
 * This includes fetching all players and saving a new player.
 * The class uses PlayerRepository to perform necessary operations related to players in the database.
 * It also logs relevant information about the operations.
 */

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerService.class);

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        LOGGER.info("Players: {}", players);
        return players;
    }

    public void save(Player player) {
        playerRepository.save(player);
    }
}

