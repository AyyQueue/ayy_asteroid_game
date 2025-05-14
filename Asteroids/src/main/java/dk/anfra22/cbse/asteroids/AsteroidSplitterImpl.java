package dk.anfra22.cbse.asteroids;

import dk.anfra22.cbse.common.asteroids.Asteroid;
import dk.anfra22.cbse.common.asteroids.IAsteroidSplitter;
import dk.anfra22.cbse.common.data.Entity;
import dk.anfra22.cbse.common.data.GameData;
import dk.anfra22.cbse.common.data.World;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.Random;

public class AsteroidSplitterImpl implements IAsteroidSplitter {

    @Override
    public void createSplitAsteroid(Entity e, World world) {
        System.out.println(e.getHealthPoints());

        if (e.getHealthPoints() == 1) {
            if (e.getPolygonCoordinates()[0] <= 8) {
                world.removeEntity(e);
            } else {
                Asteroid splitAsteroid1 = initializeSplitAsteroid(e);
                splitAsteroid1.setX(e.getX()-splitAsteroid1.getRadius()-1);
                splitAsteroid1.setY(e.getY()-splitAsteroid1.getRadius()-1);

                Asteroid splitAsteroid2 = initializeSplitAsteroid(e);
                splitAsteroid2.setX(e.getX()+splitAsteroid2.getRadius()+1);
                splitAsteroid2.setY(e.getY()+splitAsteroid2.getRadius()+1);

                world.addEntity(splitAsteroid1);
                world.addEntity(splitAsteroid2);

                world.removeEntity(e);
            }
        } else {
            e.setHealthPoints(e.getHealthPoints()-1);
        }
    }

    private Asteroid initializeSplitAsteroid(Entity e) {
        Asteroid asteroid = new Asteroid();
        Random rnd = new Random();

        double[] splitAsteroidPolygonCoordinates = e.getPolygonCoordinates();
        for (int i = 0; i<e.getPolygonCoordinates().length; i++) {
            e.getPolygonCoordinates()[i] = e.getPolygonCoordinates()[i]*0.8;
        }

        asteroid.setPolygonCoordinates(splitAsteroidPolygonCoordinates);
        asteroid.setRadius((float) splitAsteroidPolygonCoordinates[0]);

        asteroid.setRotation(rnd.nextInt(90));
        asteroid.setHealthPoints(rnd.nextInt(2,4));

        return asteroid;
    }

}
