package dk.anfra22.cbse.core;

import dk.anfra22.cbse.common.background.BackgroundSPI;
import dk.anfra22.cbse.common.services.IEntityProcessingService;
import dk.anfra22.cbse.common.services.IGamePluginService;
import dk.anfra22.cbse.common.services.IPostEntityProcessingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

@Configuration
public class SpringLoader {

    public SpringLoader(){};

    @Bean
    public GameLogic game(){
        return new GameLogic(gamePluginServices(), entityProcessingServiceList(), postEntityProcessingServices(),
                backgroundService());
    }

    @Bean
    public List<IEntityProcessingService> entityProcessingServiceList(){
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IGamePluginService> gamePluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IPostEntityProcessingService> postEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<BackgroundSPI> backgroundService() {
        return ServiceLoader.load(BackgroundSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
