import dk.anfra22.cbse.common.services.IGameDataProcessingService;
import dk.anfra22.cbse.common.services.IGamePluginService;

module CommonWeapon {
    uses dk.anfra22.cbse.common.weapon.WeaponSPI;
    exports dk.anfra22.cbse.common.weapon;
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    provides IGamePluginService with dk.anfra22.cbse.common.weapon.WeaponsPlugin;
    provides IGameDataProcessingService with dk.anfra22.cbse.common.weapon.WeaponControlProccess;
}