package dk.anfra22.cbse.weapon.canon;

import dk.anfra22.cbse.common.bullet.Bullet;
import dk.anfra22.cbse.common.data.Entity;
import dk.anfra22.cbse.common.data.World;
import dk.anfra22.cbse.common.weapon.Weapon;
import dk.anfra22.cbse.common.weapon.WeaponSPI;

import java.util.ArrayList;

public class Canon extends Weapon implements WeaponSPI {
    public Canon(){setWeaponName("Canon");}
    @Override
    public ArrayList<Entity> createShot(Entity shootingEntity, World world) {
        Bullet bigBullet = new Bullet();

        bigBullet.setPolygonCoordinates(8, -4, 8, 4, -8, 4, -8, -4);
        bigBullet.setRadius(8);
        double changeX = Math.cos(Math.toRadians(shootingEntity.getRotation()));
        double changeY = Math.sin(Math.toRadians(shootingEntity.getRotation()));
        bigBullet.setX(shootingEntity.getX() + changeX * 20);
        bigBullet.setY(shootingEntity.getY() + changeY * 20);

        bigBullet.setRotation(shootingEntity.getRotation());

        return new ArrayList<>() {{
            add(bigBullet);
        }};
    }
}
