package dk.anfra22.cbse.core;

import dk.anfra22.cbse.common.services.IGamePluginService;

import java.lang.module.ModuleFinder;
import java.nio.file.Paths;
import java.util.ServiceLoader;
import java.util.Set;

import static javafx.application.Application.launch;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

    private static ModuleLayer createLayer() {
        var appLayer = ModuleFinder.of(Paths.get("/mods-mvn"));
        var parent = ModuleLayer.boot();
        var cf = parent.configuration()
                .resolve(appLayer, ModuleFinder.of(), Set.of("Core"));

        return parent.defineModulesWithOneLoader(cf, ClassLoader.getSystemClassLoader());
    }

    public static void main(String[] args) {
        var layer = createLayer();
        var services = ServiceLoader.load(layer, IGamePluginService.class);
        services.stream().map(ServiceLoader.Provider::get).forEach(service -> launch(Main.class));
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.register(SpringLoader.class);
        configApplicationContext.refresh();


        for (String springBeans : configApplicationContext.getBeanDefinitionNames()) {
            System.out.println(springBeans);
        }

        GameLogic gameLogic = configApplicationContext.getBean(GameLogic.class);
        gameLogic.start(stage);
        gameLogic.render();

    }
}
