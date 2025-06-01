package dk.anfra22.cbse.core;

import dk.anfra22.cbse.common.background.Background;
import dk.anfra22.cbse.common.background.BackgroundSPI;
import dk.anfra22.cbse.common.data.Entity;
import dk.anfra22.cbse.common.data.GameData;
import dk.anfra22.cbse.common.data.GameKeys;
import dk.anfra22.cbse.common.data.World;
import dk.anfra22.cbse.common.services.IEntityProcessingService;
import dk.anfra22.cbse.common.services.IGameDataProcessingService;
import dk.anfra22.cbse.common.services.IGamePluginService;
import dk.anfra22.cbse.common.services.IPostEntityProcessingService;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class GameLogic extends Application {
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
//    private final Pane gameWindow = new Pane();
    private final List<IGamePluginService> gamePluginServices;
    private final List<IEntityProcessingService> entityProcessingServices;
    private final List<IPostEntityProcessingService> postEntityProcessingServices;
    private final List<BackgroundSPI> backgroundService;
    private final List<IGameDataProcessingService> gameDataProcessingServices;

    public GameLogic(List<IGamePluginService> gamePluginServices, List<IEntityProcessingService> entityProcessingServices,
                     List<IPostEntityProcessingService> postEntityProcessingServices, List<BackgroundSPI> backgroundService,
                     List<IGameDataProcessingService> gameDataProcessingService) {
        this.gamePluginServices = gamePluginServices;
        this.entityProcessingServices = entityProcessingServices;
        this.postEntityProcessingServices = postEntityProcessingServices;
        this.backgroundService = backgroundService;
        this.gameDataProcessingServices = gameDataProcessingService;
    }

    @Override
    public void start(Stage window) throws Exception {

        gameData.getGameWindow().setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());

        Scene scene = new Scene(gameData.getGameWindow());
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.A)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.D)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.W)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
            if (event.getCode().equals(KeyCode.Q)) {
                System.out.println("Q Pressed Here!");
                gameData.getKeys().setKey(GameKeys.Q, true);
            }
            if (event.getCode().equals(KeyCode.E)) {
                System.out.println("E Pressed Here!");
                gameData.getKeys().setKey(GameKeys.E, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.A)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.D)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.W)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }
            if (event.getCode().equals(KeyCode.Q)) {
                gameData.getKeys().setKey(GameKeys.Q, false);
            }
            if (event.getCode().equals(KeyCode.E)) {
                gameData.getKeys().setKey(GameKeys.E, false);
            }

        });

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : gamePluginServices) {
            iGamePlugin.start(gameData, world);
        }
        System.out.println("Debug test");

        if (backgroundService.stream().findAny().isPresent()) {;
            System.out.println("Background service started");
            ImageView background = backgroundService.stream().findAny().get().getBackground(gameData);
            gameData.getGameWindow().getChildren().add(background);
            background.toBack();
        }

        for (Entity entity : world.getEntities()) {
            System.out.println("Entity Debug Test");
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            if (entity.getColor() != null) {
                polygon.setFill(Color.valueOf(entity.getColor()));
            }
            polygons.put(entity, polygon);
            gameData.getGameWindow().getChildren().add(polygon);
        }
        render();
        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();
    }

    public void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    update();
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                draw();
                gameData.getKeys().update();
            }

        }.start();
    }

    private void update() throws IOException, URISyntaxException, InterruptedException {
        for (IEntityProcessingService entityProcessorService : entityProcessingServices) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : postEntityProcessingServices) {
            postEntityProcessorService.process(gameData, world);
        }
        for (IGameDataProcessingService gameDataProcessingService : gameDataProcessingServices) {
            gameDataProcessingService.process(gameData, world);
        }
    }

    private void draw() {
        for (Entity polygonEntity : polygons.keySet()) {
            if(!world.getEntities().contains(polygonEntity)){
                Polygon removedPolygon = polygons.get(polygonEntity);
                polygons.remove(polygonEntity);
                gameData.getGameWindow().getChildren().remove(removedPolygon);
            }
        }

        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity, polygon);
                gameData.getGameWindow().getChildren().add(polygon);
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
        }

    }
}
