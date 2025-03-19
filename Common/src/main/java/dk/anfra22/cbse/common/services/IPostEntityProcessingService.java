package dk.anfra22.cbse.common.services;

import dk.anfra22.cbse.common.data.GameData;
import dk.anfra22.cbse.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService {

    void process(GameData gameData, World world);
}