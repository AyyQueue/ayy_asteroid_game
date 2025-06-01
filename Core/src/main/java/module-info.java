module Core {
    requires Common;
    requires CommonBullet;
    requires CommonBackground;
    requires javafx.graphics;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    exports dk.anfra22.cbse.core;
    opens dk.anfra22.cbse.core to javafx.graphics, spring.core, spring.beans, spring.context;
    uses dk.anfra22.cbse.common.services.IGamePluginService;
    uses dk.anfra22.cbse.common.services.IEntityProcessingService;
    uses dk.anfra22.cbse.common.services.IPostEntityProcessingService;
    uses dk.anfra22.cbse.common.background.BackgroundSPI;
    uses dk.anfra22.cbse.common.bullet.BulletSPI;
    uses dk.anfra22.cbse.common.services.IGameDataProcessingService;
    uses dk.anfra22.cbse.common.services.SplitProvider;
}


