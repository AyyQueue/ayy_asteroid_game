package dk.anfra22.cbse.asteroids;

import dk.anfra22.cbse.common.asteroids.Asteroid;
import dk.anfra22.cbse.common.asteroids.IAsteroidSplitter;
import dk.anfra22.cbse.common.data.Entity;
import dk.anfra22.cbse.common.data.GameData;
import dk.anfra22.cbse.common.data.World;
import dk.anfra22.cbse.common.services.IGameDataProcessingService;
import dk.anfra22.cbse.common.services.IGamePluginService;
import dk.anfra22.cbse.scoringsystemplugin.IScoringSystem;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class AsteroidSplitterImpl implements IAsteroidSplitter {

    @Override
    public void createSplitAsteroid(Entity e, World world, GameData gameData) throws IOException, URISyntaxException, InterruptedException {
        System.out.println(e.getHealthPoints());

        if (e.getHealthPoints() == 1) {
            if (e.getPolygonCoordinates()[0] <= 8) {
                world.removeEntity(e);
                // If ScorePlugin exists update score
                if (getScoreImpl() != null) {
                    getScoreImpl().addScore(5);
                    getScoreImpl().updateScore(gameData);
                }
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
                // If ScorePlugin exists update score
                if (getScoreImpl() != null) {
                    getScoreImpl().addScore(10);
                    getScoreImpl().updateScore(gameData);
                }
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

    public IScoringSystem getScoreImpl(){
        if (ServiceLoader.load(IScoringSystem.class).stream().map(ServiceLoader.Provider::get).findFirst().isPresent()) {
            return ServiceLoader.load(IScoringSystem.class).stream().map(ServiceLoader.Provider::get).findFirst().get();
        } else {
            return null;
        }
    }
}
