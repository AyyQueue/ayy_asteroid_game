package dk.anfra22.cbse.core;

import dk.anfra22.cbse.common.services.IGamePluginService;
import dk.anfra22.cbse.common.services.SplitProvider;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.module.ModuleFinder;
import java.nio.file.Paths;
import java.util.ServiceLoader;
import java.util.Set;

public class Main extends Application {

    private AnnotationConfigApplicationContext context;

    @Override
    public void init() {
        context = new AnnotationConfigApplicationContext(SpringLoader.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println("Spring bean: " + beanName);
        }

        GameLogic gameLogic = context.getBean(GameLogic.class);
        gameLogic.start(stage);
        gameLogic.render();
    }

    @Override
    public void stop() {
        if (context != null) {
            context.close();
        }
    }

    private static ModuleLayer createLayer(String modsPath, String module) {
        var appLayer = ModuleFinder.of(Paths.get(modsPath));
        var parent = ModuleLayer.boot();
        var cf = parent.configuration()
                .resolve(appLayer, ModuleFinder.of(), Set.of(module));

        return parent.defineModulesWithOneLoader(cf, ClassLoader.getSystemClassLoader());
    }

    public static void main(String[] args) {
        var layer = createLayer(args[0], "split.first.provider");
        var services = ServiceLoader.load(layer, SplitProvider.class);
        services.stream().map(ServiceLoader.Provider::get).forEach(service -> System.out.println(service.helloProvider()));
        launch(args);
    }
}
