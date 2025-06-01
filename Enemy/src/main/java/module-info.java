import dk.anfra22.cbse.common.services.IEntityProcessingService;
import dk.anfra22.cbse.common.services.IGamePluginService;

module Enemy {
    requires Common;
    requires Player;
    provides IGamePluginService with dk.anfra22.cbse.enemysystem.EnemyPlugin;
    provides IEntityProcessingService with  dk.anfra22.cbse.enemysystem.EnemyControlSystem;
}
