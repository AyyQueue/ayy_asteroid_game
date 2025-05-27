module Common {
    exports dk.anfra22.cbse.common.services;
    exports dk.anfra22.cbse.common.data;
    exports dk.anfra22.cbse.common.util;
    requires javafx.graphics;
    requires spring.context;
    uses dk.anfra22.cbse.common.services.SplitProvider;
}