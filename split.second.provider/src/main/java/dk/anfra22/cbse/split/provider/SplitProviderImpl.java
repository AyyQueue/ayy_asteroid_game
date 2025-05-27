package dk.anfra22.cbse.split.provider;

import dk.anfra22.cbse.common.services.SplitProvider;

public class SplitProviderImpl implements SplitProvider {
    @Override
    public String helloProvider() {
        return "Hello from SECOND provider!";
    }
}
