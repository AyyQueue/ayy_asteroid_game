package dk.anfra22.cbse.weapon.multishot;

import dk.anfra22.cbse.common.bullet.Bullet;
import dk.anfra22.cbse.common.data.Entity;
import dk.anfra22.cbse.common.data.World;
import dk.anfra22.cbse.common.weapon.Weapon;
import dk.anfra22.cbse.common.weapon.WeaponSPI;

import java.util.ArrayList;

public class Multishot extends Weapon implements WeaponSPI {
    public Multishot() {
        setWeaponName("Multishot");
    }

    @Override
    public ArrayList<Entity> createShot(Entity shootingEntity, World world) {
        Bullet bullet1 = new Bullet();
        Bullet bullet2 = new Bullet();
        Bullet bullet3 = new Bullet();

        bullet1.setRotation(shootingEntity.getRotation() - 45);
        bullet2.setRotation(shootingEntity.getRotation());
        bullet3.setRotation(shootingEntity.getRotation() + 45);

        ArrayList<Entity> bullets = new ArrayList<>() {{
            add(bullet1);
            add(bullet2);
            add(bullet3);
        }};

        for (Entity bullet : bullets) {
            bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
            double changeX = Math.cos(Math.toRadians(shootingEntity.getRotation()));
            double changeY = Math.sin(Math.toRadians(shootingEntity.getRotation()));
            bullet.setX(shootingEntity.getX() + changeX * 10);
            bullet.setY(shootingEntity.getY() + changeY * 10);
            bullet.setRadius(1);
        }
        return bullets;
    }
}
