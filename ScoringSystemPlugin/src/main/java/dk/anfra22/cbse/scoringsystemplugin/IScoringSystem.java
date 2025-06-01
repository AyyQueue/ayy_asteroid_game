package dk.anfra22.cbse.scoringsystemplugin;

import dk.anfra22.cbse.common.data.GameData;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IScoringSystem {
    void updateScore(GameData gameData) throws URISyntaxException, IOException, InterruptedException;
    void addScore(int addPoints) throws IOException, URISyntaxException, InterruptedException;
}
