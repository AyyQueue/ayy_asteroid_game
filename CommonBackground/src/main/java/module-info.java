module CommonBackground {
    requires Common;
    requires javafx.graphics;
    requires spring.context;
    exports dk.anfra22.cbse.common.background;
    uses dk.anfra22.cbse.common.background.BackgroundSPI;
}