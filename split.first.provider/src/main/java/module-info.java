import dk.anfra22.cbse.common.services.SplitProvider;
import dk.anfra22.cbse.split.provider.SplitProviderImpl;

module split.first.provider {
    requires Common;
    provides SplitProvider with SplitProviderImpl;
}