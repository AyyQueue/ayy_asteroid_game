import dk.anfra22.cbse.common.bullet.BulletSPI;
import dk.anfra22.cbse.common.services.IEntityProcessingService;
import dk.anfra22.cbse.common.services.IGamePluginService;

module Bullet {
    requires Common;
    requires CommonBullet;
    requires spring.context;
    provides IGamePluginService with dk.anfra22.cbse.bullet.BulletPlugin;
    provides BulletSPI with dk.anfra22.cbse.bullet.BulletControlSystem;
    provides IEntityProcessingService with dk.anfra22.cbse.bullet.BulletControlSystem;
    exports dk.anfra22.cbse.bullet;
}