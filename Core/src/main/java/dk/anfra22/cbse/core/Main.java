package dk.anfra22.cbse.core;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

    private AnnotationConfigApplicationContext context;

    @Override
    public void init() {
        context = new AnnotationConfigApplicationContext(SpringLoader.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Optional: print registered beans for debugging
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

    public static void main(String[] args) {
        launch(args);
    }
}
