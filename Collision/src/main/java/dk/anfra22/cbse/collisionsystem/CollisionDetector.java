package dk.anfra22.cbse.collisionsystem;

import dk.anfra22.cbse.common.asteroids.Asteroid;
import dk.anfra22.cbse.common.asteroids.IAsteroidSplitter;
import dk.anfra22.cbse.common.data.Entity;
import dk.anfra22.cbse.common.data.GameData;
import dk.anfra22.cbse.common.data.World;
import dk.anfra22.cbse.common.services.IPostEntityProcessingService;
import dk.anfra22.cbse.common.bullet.Bullet;

import java.util.ServiceLoader;

public class CollisionDetector implements IPostEntityProcessingService {

    private final IAsteroidSplitter asteroidSplitter = ServiceLoader.load(IAsteroidSplitter.class)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No IAsteroidSplitter implementation found!"));
    // I findFirst because i only want 1 way to split asteroids (Maybe in future i can make more, where i make a a list
    // instead and randomly pick a way to split the asteroid

    public CollisionDetector() {
    }

    @Override
    public void process(GameData gameData, World world) {
        // two for loops for all entities in the world
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                // if the two entities are identical, skip the iteration
                if (entity1.getID().equals(entity2.getID())) {
                    continue;                    
                }

                if (entity1 instanceof Asteroid && entity2 instanceof Asteroid) {
                    continue;
                }

                // CollisionDetection
                if (this.collides(entity1, entity2)) {
                    System.out.println(entity1.getClass());
                    System.out.println(entity2.getClass());
                    if (entity1.getClass().equals(Bullet.class) && entity2.getClass().equals(Asteroid.class)) {
                        world.removeEntity(entity1);
                        asteroidSplitter.createSplitAsteroid(entity2, world);
                    } else if (entity1.getClass().equals(Asteroid.class) && entity2.getClass().equals(Bullet.class)) {
                        world.removeEntity(entity2);
                        asteroidSplitter.createSplitAsteroid(entity1, world);
                    } else {
                        world.removeEntity(entity1);
                        world.removeEntity(entity2);
                    }
                }
            }
        }

    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

}
