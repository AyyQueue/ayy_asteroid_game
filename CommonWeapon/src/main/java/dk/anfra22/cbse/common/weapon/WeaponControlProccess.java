package dk.anfra22.cbse.common.weapon;

import dk.anfra22.cbse.common.data.Entity;
import dk.anfra22.cbse.common.data.GameData;
import dk.anfra22.cbse.common.data.GameKeys;
import dk.anfra22.cbse.common.data.World;
import dk.anfra22.cbse.common.services.IGameDataProcessingService;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class WeaponControlProccess implements IGameDataProcessingService {

    WeaponsPlugin weaponsPlugin = new WeaponsPlugin();
    private int currentWeaponIndex = 0;
    @Override
    public void process(GameData gameData, World world) {
        if (gameData.getKeys().isPressed(GameKeys.Q)) {
            System.out.println("CurrentIndex: " + currentWeaponIndex);
            System.out.println("All Weapons size: " + weaponsPlugin.getAllWeapons().size());
            System.out.println("Q Pressed");
            if (this.currentWeaponIndex == 0) {
                this.currentWeaponIndex = weaponsPlugin.getAllWeapons().size()-1;
            }

            this.currentWeaponIndex = this.currentWeaponIndex-1;

            if (weaponsPlugin.getAllWeapons().size() > 1) {
                weaponsPlugin.setCurrentWeapon(weaponsPlugin.getAllWeapons().get(this.currentWeaponIndex));
                updateText(gameData);
            }

        }
        if (gameData.getKeys().isPressed(GameKeys.E)) {
            System.out.println("CurrentIndex: " + currentWeaponIndex);
            System.out.println("All Weapons size: " + weaponsPlugin.getAllWeapons().size());
            System.out.println("E Pressed");
            if (this.currentWeaponIndex == weaponsPlugin.getAllWeapons().size() - 1) {
                this.currentWeaponIndex = 0;
            }

            this.currentWeaponIndex = this.currentWeaponIndex+1;

            if (weaponsPlugin.getAllWeapons().size() > 1) {
                weaponsPlugin.setCurrentWeapon(weaponsPlugin.getAllWeapons().get(this.currentWeaponIndex));
                updateText(gameData);
            }
        }
    }

    private void updateText(GameData gameData) {
        for (Node node : gameData.getGameWindow().getChildren()) {
            String nodeId = node.getId();
            if ("currentWeaponText".equals(nodeId)) {
                    Text currentWeaponText = (Text) node;
                currentWeaponText.setText("Current Weapon: " + weaponsPlugin.getCurrentWeapon().getClass().getSimpleName());
            }
        }
    }
}
