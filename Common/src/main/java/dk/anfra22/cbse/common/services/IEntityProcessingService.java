package dk.anfra22.cbse.common.services;

import dk.anfra22.cbse.common.data.GameData;
import dk.anfra22.cbse.common.data.World;
import org.springframework.stereotype.Component;

@Component
public interface IEntityProcessingService {
    void process(GameData gameData, World world);
}