import dk.anfra22.cbse.background.BackgroundPlugin;
import dk.anfra22.cbse.common.services.IGamePluginService;
import dk.anfra22.cbse.common.background.BackgroundSPI;

module Background {
    requires Common;
    requires CommonBackground;
    requires javafx.graphics;
    requires spring.context;
    provides BackgroundSPI with BackgroundPlugin;
}