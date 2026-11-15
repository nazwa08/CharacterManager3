
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.CharacterType;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class AppTheme {

    // WARNA UTAMA
    public static final Color BACKGROUND = new Color(24, 27, 38);
    public static final Color PANEL_BACKGROUND = new Color(35, 40, 54);
    public static final Color TEXT_COLOR = new Color(240, 240, 245);
    public static final Color GOLD = new Color(255, 196, 55);
    public static final Color BUTTON_COLOR = new Color(65, 105, 225);

    // WARNA BERDASARKAN ROLE
    public static final Color WARRIOR_COLOR = new Color(150, 55, 65);
    public static final Color MAGE_COLOR = new Color(55, 90, 165);
    public static final Color ARCHER_COLOR = new Color(45, 125, 85);
    public static final Color ASSASSIN_COLOR = new Color(105, 70, 145);

    // FONT
    public static final Font FONT_TITLE =
            new Font(Font.SANS_SERIF, Font.BOLD, 24);

    public static final Font FONT_SUBTITLE =
            new Font(Font.SANS_SERIF, Font.BOLD, 17);

    public static final Font FONT_BODY =
            new Font(Font.SANS_SERIF, Font.PLAIN, 14);

    // BORDER
    public static final Border CARD_BORDER =
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                            new Color(85, 90, 110), 1),
                    BorderFactory.createEmptyBorder(12, 12, 12, 12)
            );

    // BATAS LEVEL TINGGI
    public static final int HIGH_LEVEL_THRESHOLD = 5;

    // METHOD WARNA ROLE
    public static Color colorFor(CharacterType type) {

        if (type == null) {
            return PANEL_BACKGROUND;
        }

        switch (type) {
            case WARRIOR:
                return WARRIOR_COLOR;

            case MAGE:
                return MAGE_COLOR;

            case ARCHER:
                return ARCHER_COLOR;

            case ASSASSIN:
                return ASSASSIN_COLOR;

            default:
                return PANEL_BACKGROUND;
        }
    }

    private AppTheme() {
        // Mencegah pembuatan objek AppTheme
    }
}
