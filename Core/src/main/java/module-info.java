module Core {
    requires Common;
    requires CommonBullet;
    requires CommonBackground;
    requires javafx.graphics;
    opens dk.anfra22.cbse.core to javafx.graphics;
    uses dk.anfra22.cbse.common.services.IGamePluginService;
    uses dk.anfra22.cbse.common.services.IEntityProcessingService;
    uses dk.anfra22.cbse.common.services.IPostEntityProcessingService;
    uses dk.anfra22.cbse.common.background.BackgroundSPI;
    uses dk.anfra22.cbse.common.bullet.BulletSPI;
}


