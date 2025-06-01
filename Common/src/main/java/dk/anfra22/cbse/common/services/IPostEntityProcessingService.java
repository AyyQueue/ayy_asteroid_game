package dk.anfra22.cbse.common.services;

import dk.anfra22.cbse.common.data.GameData;
import dk.anfra22.cbse.common.data.World;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public interface IPostEntityProcessingService {

    void process(GameData gameData, World world) throws IOException, URISyntaxException, InterruptedException;
}