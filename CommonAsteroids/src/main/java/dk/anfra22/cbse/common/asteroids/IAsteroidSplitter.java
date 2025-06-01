package dk.anfra22.cbse.common.asteroids;

import dk.anfra22.cbse.common.data.Entity;
import dk.anfra22.cbse.common.data.GameData;
import dk.anfra22.cbse.common.data.World;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author corfixen
 */
@Component
public interface IAsteroidSplitter {
    void createSplitAsteroid(Entity e, World w, GameData gameData) throws IOException, URISyntaxException, InterruptedException;
}