import dk.anfra22.cbse.common.services.IPostEntityProcessingService;

module Collision {
    uses dk.anfra22.cbse.common.asteroids.IAsteroidSplitter;
    requires Common;
    requires CommonAsteroids;
    requires CommonBullet;
    provides IPostEntityProcessingService with dk.anfra22.cbse.collisionsystem.CollisionDetector;
    exports dk.anfra22.cbse.collisionsystem;
}