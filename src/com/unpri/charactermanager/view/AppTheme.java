
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.CharacterType;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class AppTheme {

    // Warna aplikasi
    public static final Color BACKGROUND = new Color(245, 247, 250);
    public static final Color PANEL = Color.WHITE;
    public static final Color TEXT = new Color(35, 42, 52);
    public static final Color MUTED = new Color(110, 120, 135);
    public static final Color ACCENT = new Color(45, 105, 180);
    public static final Color BORDER_COLOR = new Color(220, 225, 232);

    // Font aplikasi
    public static final Font TITLE_FONT =
            new Font("SansSerif", Font.BOLD, 23);

    public static final Font SUBTITLE_FONT =
            new Font("SansSerif", Font.PLAIN, 13);

    public static final Font CARD_TITLE_FONT =
            new Font("SansSerif", Font.BOLD, 16);

    public static final Font BODY_FONT =
            new Font("SansSerif", Font.PLAIN, 13);

    public static final Font SMALL_FONT =
            new Font("SansSerif", Font.PLAIN, 11);

    // Border aplikasi
    public static final Border PANEL_BORDER =
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BORDER_COLOR),
                    BorderFactory.createEmptyBorder(12, 12, 12, 12)
            );

    private AppTheme() {
    }

    // Warna berdasarkan tipe karakter
    public static Color colorFor(CharacterType type) {

        if (type == null) {
            return MUTED;
        }

        switch (type) {
            case WARRIOR:
                return new Color(220, 85, 85);

            case MAGE:
                return new Color(125, 90, 205);

            case ARCHER:
                return new Color(55, 155, 100);

            case ASSASSIN:
                return new Color(65, 75, 95);

            default:
                return MUTED;
        }
    }
}
