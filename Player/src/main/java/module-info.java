import dk.anfra22.cbse.common.services.IEntityProcessingService;
import dk.anfra22.cbse.common.services.IGamePluginService;

module Player {
    exports dk.anfra22.cbse.playersystem;
    requires Common;
    requires CommonBullet;
    uses dk.anfra22.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with dk.anfra22.cbse.playersystem.PlayerPlugin;
    provides IEntityProcessingService with dk.anfra22.cbse.playersystem.PlayerControlSystem;

}