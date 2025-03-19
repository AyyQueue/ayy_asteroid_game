import dk.anfra22.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    provides IPostEntityProcessingService with dk.anfra22.cbse.collisionsystem.CollisionDetector;
}