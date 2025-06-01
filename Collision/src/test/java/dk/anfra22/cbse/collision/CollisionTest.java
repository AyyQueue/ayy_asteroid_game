package dk.anfra22.cbse.collision;

import dk.anfra22.cbse.collisionsystem.CollisionDetector;
import dk.anfra22.cbse.common.asteroids.Asteroid;
import dk.anfra22.cbse.common.asteroids.IAsteroidSplitter;
import dk.anfra22.cbse.common.bullet.Bullet;
import dk.anfra22.cbse.common.data.Entity;
import dk.anfra22.cbse.common.data.GameData;
import dk.anfra22.cbse.common.data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.ServiceLoader;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CollisionTest {

    private CollisionDetector collisionDetector;
    private GameData gameData;
    private World world;
    @BeforeEach
    void setUp() {
        gameData = new GameData();
        world = new World();

        IAsteroidSplitter mockSplitter = mock(IAsteroidSplitter.class);
        @SuppressWarnings("unchecked")
        ServiceLoader<IAsteroidSplitter> mockedLoader = mock(ServiceLoader.class);
        when(mockedLoader.findFirst()).thenReturn(Optional.of(mockSplitter));

        try (MockedStatic<ServiceLoader> mockedStatic = Mockito.mockStatic(ServiceLoader.class)) {
            mockedStatic.when(() -> ServiceLoader.load(IAsteroidSplitter.class)).thenReturn(mockedLoader);
            collisionDetector = new CollisionDetector();  // uses the mocked loader
        }
    }

    @Test
    void testEntityCollision() throws IOException, URISyntaxException, InterruptedException {
        Bullet bullet = new Bullet();
        bullet.setX(100);
        bullet.setY(100);
        bullet.setRadius(5);

        Entity entity = new Entity();
        // Set the position to be different, but still within the collision detection range
        entity.setX(102);
        entity.setY(100);
        entity.setRadius(5);

        world.addEntity(bullet);
        world.addEntity(entity);

        // Assert if the entities have been added to the world
        assert(!world.getEntities().isEmpty());

        collisionDetector.process(gameData, world);

        // Assert if the entities have been removed from the world as they should collide
        assert(world.getEntities().isEmpty());
    }
}
