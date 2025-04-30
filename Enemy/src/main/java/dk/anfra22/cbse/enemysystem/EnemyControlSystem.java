package dk.anfra22.cbse.enemysystem;

import dk.anfra22.cbse.common.bullet.BulletSPI;
import dk.anfra22.cbse.common.data.Entity;
import dk.anfra22.cbse.common.data.GameData;
import dk.anfra22.cbse.common.data.World;
import dk.anfra22.cbse.common.services.IEntityProcessingService;
import dk.anfra22.cbse.playersystem.Player;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        Entity player = world.getEntities(Player.class).stream().findFirst().orElse(null);
        for (Entity enemy : world.getEntities(Enemy.class)) {
            if (player != null) {
                // This is the arctan2 formula to triangulate the angle between 2 points
                // I use it for setting the rotation of the enemy, so that it always points at the player
                double deltaX = player.getX() - enemy.getX();
                double deltaY = player.getY() - enemy.getY();

                double rotationAngle = Math.atan2(deltaY, deltaX);

                enemy.setRotation(Math.toDegrees(rotationAngle));

                // Sets the enemy to move
                double changeX = Math.cos(Math.toRadians(enemy.getRotation())) * 0.7; // Enemy should be a bit slower than player
                double changeY = Math.sin(Math.toRadians(enemy.getRotation())) * 0.7;
                enemy.setX(enemy.getX() + changeX);
                enemy.setY(enemy.getY() + changeY);


            }

            if (enemy.getX() < 0) {
                enemy.setX(1);
            }


            //Stop the enemy if it hits the display border
            if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(gameData.getDisplayWidth()-1);
            }

            if (enemy.getY() < 0) {
                enemy.setY(1);
            }

            if (enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(gameData.getDisplayHeight()-1);
            }


        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
