package dk.anfra22.cbse.common.weapon;

import dk.anfra22.cbse.common.bullet.Bullet;
import dk.anfra22.cbse.common.data.Entity;
import dk.anfra22.cbse.common.data.GameData;
import dk.anfra22.cbse.common.data.World;

import java.util.ArrayList;
import java.util.List;

public interface WeaponSPI {
    ArrayList<Entity> createShot(Entity shootingEntity, World world);
}
