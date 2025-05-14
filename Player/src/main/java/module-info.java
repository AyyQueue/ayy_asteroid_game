import dk.anfra22.cbse.common.services.IEntityProcessingService;
import dk.anfra22.cbse.common.services.IGamePluginService;

module Player {
    exports dk.anfra22.cbse.playersystem;
    requires Common;
    requires CommonBullet;
    requires CommonWeapon;
    requires spring.context;
    uses dk.anfra22.cbse.common.bullet.BulletSPI;
    uses dk.anfra22.cbse.common.weapon.WeaponSPI;
    uses IGamePluginService;
    provides IGamePluginService with dk.anfra22.cbse.playersystem.PlayerPlugin;
    provides IEntityProcessingService with dk.anfra22.cbse.playersystem.PlayerControlSystem;

}