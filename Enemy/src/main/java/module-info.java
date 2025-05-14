import dk.anfra22.cbse.common.services.IEntityProcessingService;
import dk.anfra22.cbse.common.services.IGamePluginService;

module Enemy {
    requires Common;
    requires CommonBullet;
    requires Player;
    requires spring.context;
    uses dk.anfra22.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with dk.anfra22.cbse.enemysystem.EnemyPlugin;
    provides IEntityProcessingService with  dk.anfra22.cbse.enemysystem.EnemyControlSystem;
    exports dk.anfra22.cbse.enemysystem;

}
