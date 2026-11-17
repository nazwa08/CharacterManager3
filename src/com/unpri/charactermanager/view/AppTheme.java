
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AppTheme {

    // Background dan warna utama
    public static final Color BACKGROUND =
            new Color(13, 17, 28);

    public static final Color PANEL_BACKGROUND =
            new Color(24, 31, 48);

    public static final Color PRIMARY =
            new Color(245, 190, 65);

    public static final Color DARK =
            new Color(255, 213, 105);

    public static final Color WHITE =
            new Color(255, 255, 255);

    public static final Color TEXT =
            new Color(225, 231, 240);

    public static final Color SECONDARY =
            new Color(160, 172, 190);

    public static final Color SUCCESS =
            new Color(55, 210, 130);

    public static final Color ERROR =
            new Color(255, 85, 95);

    // Warna form
    public static final Color FORM_BACKGROUND =
            new Color(24, 31, 48);

    public static final Color INPUT_BACKGROUND =
            new Color(38, 47, 66);

    // Warna kartu berdasarkan role
    public static final Color WARRIOR =
            new Color(105, 39, 52);

    public static final Color MAGE =
            new Color(43, 66, 120);

    public static final Color ARCHER =
            new Color(30, 100, 83);

    public static final Color ASSASSIN =
            new Color(91, 53, 120);

    // Font
    public static final Font TITLE_FONT =
            new Font("Segoe UI", Font.BOLD, 27);

    public static final Font NORMAL_FONT =
            new Font("Segoe UI", Font.PLAIN, 14);

    public static final Font BUTTON_FONT =
            new Font("Segoe UI", Font.BOLD, 14);

    public static final Font CARD_FONT =
            new Font("Segoe UI", Font.BOLD, 16);

    // Border
    public static final Border PANEL_BORDER =
            BorderFactory.createEmptyBorder(18, 18, 18, 18);

    public static final Border FORM_BORDER =
            BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(PRIMARY, 2),
                    "TAMBAH KARAKTER",
                    0,
                    0,
                    BUTTON_FONT,
                    PRIMARY
            );

    public static final Border INPUT_BORDER =
            BorderFactory.createLineBorder(
                    new Color(85, 99, 122), 1
            );

    public static final Border ERROR_BORDER =
            BorderFactory.createLineBorder(ERROR, 2);

    public static final Border CARD_BORDER =
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                            new Color(245, 190, 65), 1
                    ),
                    BorderFactory.createEmptyBorder(
                            13, 15, 13, 15
                    )
            );

    // Mengembalikan warna sesuai tipe karakter
    public static Color colorFor(CharacterType type) {

        if (type == null) {
            return PANEL_BACKGROUND;
        }

        switch (type) {
            case WARRIOR:
                return WARRIOR;

            case MAGE:
                return MAGE;

            case ARCHER:
                return ARCHER;

            case ASSASSIN:
                return ASSASSIN;

            default:
                return PANEL_BACKGROUND;
        }
    }
}
