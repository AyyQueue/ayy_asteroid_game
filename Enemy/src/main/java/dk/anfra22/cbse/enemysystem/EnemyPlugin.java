package dk.anfra22.cbse.enemysystem;

import dk.anfra22.cbse.common.data.Entity;
import dk.anfra22.cbse.common.data.GameData;
import dk.anfra22.cbse.common.data.World;
import dk.anfra22.cbse.common.services.IGamePluginService;
import dk.anfra22.cbse.playersystem.Player;

import java.util.Random;

public class EnemyPlugin implements IGamePluginService {


    private Entity enemy;

    public EnemyPlugin() {
    }

        @Override
        public void start(GameData gameData, World world) {

        // Add entities to the world
        enemy = createEnemyShip(gameData, world);
        world.addEntity(enemy);
    }

        private Entity createEnemyShip(GameData gameData, World world) {

        Entity enemyShip = new Enemy();
        Random random = new Random();
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemyShip.setX(random.nextInt(gameData.getDisplayHeight()));
        enemyShip.setY(random.nextInt(gameData.getDisplayWidth()));
        enemyShip.setRadius(8);

        Entity player = world.getEntities(Player.class).stream().findFirst().orElse(null);

            if (player != null) {
                double deltaX = player.getX() - enemy.getX();
                double deltaY = player.getY() - enemy.getY();

                double rotationAngle = Math.atan2(deltaY, deltaX);

                enemy.setRotation(Math.toDegrees(rotationAngle));


            }
        enemyShip.setColor("RED");
        return enemyShip;
    }

        @Override
        public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }

    }
