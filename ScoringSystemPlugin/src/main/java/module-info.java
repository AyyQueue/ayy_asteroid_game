import dk.anfra22.cbse.common.services.IGamePluginService;
import dk.anfra22.cbse.scoringsystemplugin.IScoringSystem;

module ScoringSystemPlugin {
    exports dk.anfra22.cbse.scoringsystemplugin;
    requires Common;
    requires javafx.graphics;
    requires java.net.http;
    provides IGamePluginService with dk.anfra22.cbse.scoringsystemplugin.ScoringSystemPlugin;
    provides IScoringSystem with dk.anfra22.cbse.scoringsystemplugin.ScoringSystemPlugin;
}