
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

            // WARRIOR
            manager.addCharacter(
                    new Character("Arthur", CharacterType.WARRIOR, 10)
            );

            manager.addCharacter(
                    new Character("Leon", CharacterType.WARRIOR, 5)
            );

            // MAGE
            manager.addCharacter(
                    new Character("Merlin", CharacterType.MAGE, 15)
            );

            manager.addCharacter(
                    new Character("Luna", CharacterType.MAGE, 8)
            );

            // ARCHER
            manager.addCharacter(
                    new Character("Robin", CharacterType.ARCHER, 7)
            );

            manager.addCharacter(
                    new Character("Diana", CharacterType.ARCHER, 12)
            );

            // ASSASSIN
            manager.addCharacter(
                    new Character("Shadow", CharacterType.ASSASSIN, 9)
            );

            manager.addCharacter(
                    new Character("Akira", CharacterType.ASSASSIN, 6)
            );

            MainWindow window = new MainWindow(manager);
            window.setVisible(true);

        });
    }
}
