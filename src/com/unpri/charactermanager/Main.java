
package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            CharacterManager manager = new CharacterManager();

            // 6 KARAKTER DENGAN ROLE DAN LEVEL BERBEDA
            manager.addCharacter(
                    new Character("Arthur", CharacterType.WARRIOR, 1)
            );

            manager.addCharacter(
                    new Character("Merlin", CharacterType.MAGE, 3)
            );

            manager.addCharacter(
                    new Character("Robin", CharacterType.ARCHER, 2)
            );

            manager.addCharacter(
                    new Character("Luna", CharacterType.MAGE, 4)
            );

            manager.addCharacter(
                    new Character("Ragnar", CharacterType.WARRIOR, 5)
            );

            manager.addCharacter(
                    new Character("Shadow", CharacterType.ASSASSIN, 6)
            );

            // MEMBUKA GUI
            MainWindow window = new MainWindow(manager);
            window.setVisible(true);
        });
    }
}
