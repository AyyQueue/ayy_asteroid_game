package dk.anfra22.cbse.asteroids;

import dk.anfra22.cbse.common.asteroids.Asteroid;
import dk.anfra22.cbse.common.data.Entity;
import dk.anfra22.cbse.common.data.GameData;
import dk.anfra22.cbse.common.data.World;
import dk.anfra22.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        Random rnd = new Random();
        for(int i = 0; i< rnd.nextInt(2,10); i++) {
            Entity asteroid = createAsteroid(gameData);
            asteroid.setHealthPoints(rnd.nextInt(2,4));
            world.addEntity(asteroid);
        }

    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {
        Asteroid asteroid = new Asteroid();
        Random rnd = new Random();
        int size = rnd.nextInt(15) + 8;
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(rnd.nextInt(0, gameData.getDisplayWidth()));
        asteroid.setY(rnd.nextInt(0, gameData.getDisplayHeight()));
        asteroid.setRadius(size);
        asteroid.setRotation(rnd.nextInt(90));
        asteroid.setHealthPoints(rnd.nextInt(1,4));

        return asteroid;
    }
}
