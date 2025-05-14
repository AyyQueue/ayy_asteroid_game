import dk.anfra22.cbse.common.services.IGameDataProcessingService;
import dk.anfra22.cbse.common.services.IGamePluginService;

module CommonWeapon {
    uses dk.anfra22.cbse.common.weapon.Weapon;
    uses dk.anfra22.cbse.common.weapon.WeaponSPI;
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    exports dk.anfra22.cbse.common.weapon;
    provides IGamePluginService with dk.anfra22.cbse.common.weapon.WeaponsPlugin;
    provides IGameDataProcessingService with dk.anfra22.cbse.common.weapon.WeaponControlProccess;
}