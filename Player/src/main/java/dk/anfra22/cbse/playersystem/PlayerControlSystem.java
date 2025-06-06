package dk.anfra22.cbse.playersystem;

import dk.anfra22.cbse.common.bullet.Bullet;
import dk.anfra22.cbse.common.bullet.BulletSPI;
import dk.anfra22.cbse.common.data.Entity;
import dk.anfra22.cbse.common.data.GameData;
import dk.anfra22.cbse.common.data.GameKeys;
import dk.anfra22.cbse.common.data.World;
import dk.anfra22.cbse.common.services.IEntityProcessingService;
import dk.anfra22.cbse.common.services.IGamePluginService;
import dk.anfra22.cbse.common.weapon.WeaponSPI;
import dk.anfra22.cbse.common.weapon.WeaponsPlugin;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

@Component
public class PlayerControlSystem implements IEntityProcessingService {

    private int bulletCooldown = 0;

    @Override
    public void process(GameData gameData, World world) {



        for (Entity player : world.getEntities(Player.class)) {
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - 2);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + 2);
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                double changeX = Math.cos(Math.toRadians(player.getRotation()));
                double changeY = Math.sin(Math.toRadians(player.getRotation()));
                player.setX(player.getX() + changeX);
                player.setY(player.getY() + changeY);
            }
            if(gameData.getKeys().isDown(GameKeys.SPACE)) {
                if (bulletCooldown == 0) {
                    System.out.println(getWeaponSPIs());
                    WeaponsPlugin weaponsPlugin = WeaponsPlugin.getInstance();
                    if (weaponsPlugin != null && weaponsPlugin.getCurrentWeapon() != null) {
                        for (Entity bullet : weaponsPlugin.getCurrentWeapon().createShot(player, world)) {
                            world.addEntity(bullet);
                        }
                    } else {
                        getBulletSPIs().stream().findFirst().ifPresent(
                                spi -> world.addEntity(spi.createBullet(player, gameData))
                        );
                    }
                    bulletCooldown = 10;
                }
                bulletCooldown-=1;
            }

            if (player.getX() < 0) {
                player.setX(1);
            }

            if (player.getX() > gameData.getDisplayWidth()) {
                player.setX(gameData.getDisplayWidth()-1);
            }

            if (player.getY() < 0) {
                player.setY(1);
            }

            if (player.getY() > gameData.getDisplayHeight()) {
                player.setY(gameData.getDisplayHeight()-1);
            }


        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends WeaponSPI> getWeaponSPIs() {
        return ServiceLoader.load(WeaponSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}