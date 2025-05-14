import dk.anfra22.cbse.common.asteroids.IAsteroidSplitter;
import dk.anfra22.cbse.common.services.IEntityProcessingService;
import dk.anfra22.cbse.common.services.IGamePluginService;

module Asteroid {
    requires Common;
    requires CommonAsteroids;
    requires spring.context;
    requires javafx.graphics;
    provides IGamePluginService with dk.anfra22.cbse.asteroids.AsteroidPlugin;
    provides IEntityProcessingService with dk.anfra22.cbse.asteroids.AsteroidProcessor;
    provides IAsteroidSplitter with dk.anfra22.cbse.asteroids.AsteroidSplitterImpl;
    exports dk.anfra22.cbse.asteroids;
}