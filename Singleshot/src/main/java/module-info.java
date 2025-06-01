import dk.anfra22.cbse.common.weapon.WeaponSPI;

module Singleshot {
    requires Common;
    requires CommonBullet;
    requires CommonWeapon;
    provides WeaponSPI with dk.anfra22.cbse.weapon.singleshot.Singleshot;

}