
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

            // DATA KARAKTER AWAL
            manager.addCharacter(
                    new Character("Arthur", CharacterType.WARRIOR, 1));

            manager.addCharacter(
                    new Character("Merlin", CharacterType.MAGE, 3));

            manager.addCharacter(
                    new Character("Robin", CharacterType.ARCHER, 2));

            manager.addCharacter(
                    new Character("Lancelot", CharacterType.WARRIOR, 4));

            manager.addCharacter(
                    new Character("Luna", CharacterType.MAGE, 5));

            // MEMBUKA JENDELA GUI
            MainWindow window = new MainWindow(manager);

            window.setVisible(true);
        });
    }
}
