import dk.anfra22.cbse.background.BackgroundPlugin;
import dk.anfra22.cbse.common.services.IGamePluginService;
import dk.anfra22.cbse.common.background.BackgroundSPI;

module Background {
    requires Common;
    requires CommonBackground;
    requires javafx.graphics;
    provides BackgroundSPI with BackgroundPlugin;
}