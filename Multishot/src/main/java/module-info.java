import dk.anfra22.cbse.common.weapon.WeaponSPI;

module Multishot {
    requires Common;
    requires CommonBullet;
    requires CommonWeapon;
    provides WeaponSPI with dk.anfra22.cbse.weapon.multishot.Multishot;

}