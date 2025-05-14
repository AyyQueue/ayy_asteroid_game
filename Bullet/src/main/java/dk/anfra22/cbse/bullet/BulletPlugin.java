package dk.anfra22.cbse.bullet;

import dk.anfra22.cbse.common.bullet.Bullet;
import dk.anfra22.cbse.common.data.Entity;
import dk.anfra22.cbse.common.data.GameData;
import dk.anfra22.cbse.common.data.World;
import dk.anfra22.cbse.common.services.IGamePluginService;
import org.springframework.stereotype.Component;

@Component
public class BulletPlugin implements IGamePluginService {

    private Entity bullet;

    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Bullet.class) {
                world.removeEntity(e);
            }
        }
    }

}
