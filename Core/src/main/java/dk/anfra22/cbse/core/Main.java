package dk.anfra22.cbse.core;

import dk.anfra22.cbse.common.services.SplitProvider;
import dk.anfra22.cbse.common.util.SplitProviderLocator;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

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

        // Load core/plugin services from unified SplitProviderLocator
        List<SplitProvider> splitProviders = SplitProviderLocator.INSTANCE.locateAll(SplitProvider.class);
        for (SplitProvider provider : splitProviders) {
            System.out.println("Loaded provider says: " + provider.helloProvider());
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

    public static void main(String[] args) {
        launch(args);  // JavaFX entry point
    }
}
