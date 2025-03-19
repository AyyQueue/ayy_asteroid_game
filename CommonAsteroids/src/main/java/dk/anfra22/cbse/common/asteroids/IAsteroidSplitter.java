package dk.anfra22.cbse.common.asteroids;

import dk.anfra22.cbse.common.data.Entity;
import dk.anfra22.cbse.common.data.World;

/**
 *
 * @author corfixen
 */
public interface IAsteroidSplitter {
    void createSplitAsteroid(Entity e, World w);
}