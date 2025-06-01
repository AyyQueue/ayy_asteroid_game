package dk.anfra22.cbse.weapon.singleshot;

import dk.anfra22.cbse.common.bullet.Bullet;
import dk.anfra22.cbse.common.data.Entity;
import dk.anfra22.cbse.common.data.World;
import dk.anfra22.cbse.common.weapon.WeaponSPI;

import java.util.ArrayList;
public class Singleshot implements WeaponSPI {
    @Override
    public ArrayList<Entity> createShot(Entity shootingEntity, World world) {
        Bullet bullet = new Bullet();

        bullet.setRotation(shootingEntity.getRotation());
        bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
        double changeX = Math.cos(Math.toRadians(shootingEntity.getRotation()));
        double changeY = Math.sin(Math.toRadians(shootingEntity.getRotation()));
        bullet.setX(shootingEntity.getX() + changeX * 10);
        bullet.setY(shootingEntity.getY() + changeY * 10);
        bullet.setRadius(1);

        return new ArrayList<>() {{
            add(bullet);
        }};
    }
}
