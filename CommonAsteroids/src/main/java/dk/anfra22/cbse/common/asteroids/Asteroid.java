package dk.anfra22.cbse.common.asteroids;

import dk.anfra22.cbse.common.data.Entity;

/**
 *
 * @author corfixen
 */
public class Asteroid extends Entity {
    private int lifeAmount;

    public int getLifeAmount() {
        return lifeAmount;
    }

    public void setLifeAmount(int lifeAmount) {
        this.lifeAmount = lifeAmount;
    }
}
