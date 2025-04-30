import dk.anfra22.cbse.common.services.IPostEntityProcessingService;

module Collision {
    uses dk.anfra22.cbse.common.asteroids.IAsteroidSplitter;
    requires Common;
    requires spring.context;
    requires spring.beans;
    requires CommonAsteroids;
    requires CommonBullet;
    provides IPostEntityProcessingService with dk.anfra22.cbse.collisionsystem.CollisionDetector;
}