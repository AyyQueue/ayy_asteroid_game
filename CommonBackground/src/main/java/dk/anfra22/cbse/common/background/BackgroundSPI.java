package dk.anfra22.cbse.common.background;

import dk.anfra22.cbse.common.data.GameData;
import javafx.scene.image.ImageView;

public interface BackgroundSPI {
     ImageView getBackground(GameData gameData);
}
