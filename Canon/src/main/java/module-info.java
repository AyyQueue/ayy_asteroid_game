import dk.anfra22.cbse.common.weapon.WeaponSPI;

module Canon {
    requires Common;
    requires CommonWeapon;
    requires CommonBullet;
    provides WeaponSPI with dk.anfra22.cbse.weapon.canon.Canon;
}