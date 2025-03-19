package dk.anfra22.cbse.common.bullet;

import dk.anfra22.cbse.common.data.Entity;
import dk.anfra22.cbse.common.data.GameData;

/**
 *
 * @author corfixen
 */
public interface BulletSPI {
    Entity createBullet(Entity e, GameData gameData);
}