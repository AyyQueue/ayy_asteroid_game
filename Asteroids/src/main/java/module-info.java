import dk.anfra22.cbse.common.services.IEntityProcessingService;
import dk.anfra22.cbse.common.services.IGamePluginService;

module Asteroid {
    requires Common;
    requires CommonAsteroids;
    provides IGamePluginService with dk.anfra22.cbse.asteroids.AsteroidPlugin;
    provides IEntityProcessingService with dk.anfra22.cbse.asteroids.AsteroidProcessor;
}