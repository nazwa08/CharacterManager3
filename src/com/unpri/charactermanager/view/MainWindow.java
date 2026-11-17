
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private final CharacterManager manager;
    private final JPanel daftarPanel;
    private final JLabel statistikLabel;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager - TP14");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel utama = new JPanel(new BorderLayout(15, 15));
        utama.setBackground(AppTheme.BACKGROUND);
        utama.setBorder(AppTheme.PANEL_BORDER);

        // HEADER
        JPanel bagianAtas = new JPanel(new BorderLayout(5, 8));
        bagianAtas.setBackground(AppTheme.BACKGROUND);

        JLabel judul = new JLabel(
                "CHARACTER MANAGER  |  ROSTER"
        );

        judul.setFont(AppTheme.TITLE_FONT);
        judul.setForeground(AppTheme.PRIMARY);

        JLabel subJudul = new JLabel(
                "Manage your heroes and build your team"
        );

        subJudul.setFont(AppTheme.NORMAL_FONT);
        subJudul.setForeground(AppTheme.SECONDARY);

        statistikLabel = new JLabel();
        statistikLabel.setFont(AppTheme.BUTTON_FONT);
        statistikLabel.setForeground(AppTheme.WHITE);

        bagianAtas.add(judul, BorderLayout.NORTH);
        bagianAtas.add(subJudul, BorderLayout.CENTER);
        bagianAtas.add(statistikLabel, BorderLayout.SOUTH);

        // DAFTAR KARAKTER
        daftarPanel = new JPanel();
        daftarPanel.setBackground(AppTheme.BACKGROUND);
        daftarPanel.setLayout(
                new BoxLayout(daftarPanel, BoxLayout.Y_AXIS)
        );

        JScrollPane scrollPane = new JScrollPane(daftarPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(
                AppTheme.BACKGROUND
        );
        scrollPane.setBackground(AppTheme.BACKGROUND);

        // LEGENDA ROLE
        JPanel legenda = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 12, 5)
        );
        legenda.setBackground(AppTheme.PANEL_BACKGROUND);

        tambahLegenda(legenda, "WARRIOR", AppTheme.WARRIOR);
        tambahLegenda(legenda, "MAGE", AppTheme.MAGE);
        tambahLegenda(legenda, "ARCHER", AppTheme.ARCHER);
        tambahLegenda(legenda, "ASSASSIN", AppTheme.ASSASSIN);

        // PANEL FORM
        CharacterFormPanel formPanel =
                new CharacterFormPanel(
                        manager,
                        this::refreshList
                );

        // BAGIAN BAWAH
        JPanel bagianBawah = new JPanel(
                new BorderLayout(5, 8)
        );

        bagianBawah.setBackground(AppTheme.BACKGROUND);
        bagianBawah.add(legenda, BorderLayout.NORTH);
        bagianBawah.add(formPanel, BorderLayout.CENTER);

        utama.add(bagianAtas, BorderLayout.NORTH);
        utama.add(scrollPane, BorderLayout.CENTER);
        utama.add(bagianBawah, BorderLayout.SOUTH);

        add(utama);

        refreshList();

        pack();
        setMinimumSize(new Dimension(750, 600));
        setLocationRelativeTo(null);
    }

    private void tambahLegenda(
            JPanel panel,
            String tulisan,
            Color warna) {

        JLabel label = new JLabel(tulisan);
        label.setForeground(AppTheme.WHITE);
        label.setFont(AppTheme.NORMAL_FONT);
        label.setOpaque(true);
        label.setBackground(warna);
        label.setBorder(AppTheme.INPUT_BORDER);

        panel.add(label);
    }

    public void refreshList() {

        daftarPanel.removeAll();

        for (Character character : manager.getAllCharacters()) {

            JPanel kartu = new JPanel(
                    new BorderLayout(10, 8)
            );

            kartu.setBackground(
                    AppTheme.colorFor(character.getType())
            );

            kartu.setOpaque(true);
            kartu.setBorder(AppTheme.CARD_BORDER);

            JLabel nama = new JLabel(
                    character.getName()
                    + "  |  "
                    + character.getType()
            );

            nama.setFont(AppTheme.CARD_FONT);
            nama.setForeground(AppTheme.WHITE);

            JLabel detail = new JLabel(
                    "LEVEL: " + character.getLevel()
                    + "     |     HP: " + character.getHitPoints()
                    + "     |     ATTACK: " + character.getAttack()
                    + "     |     MANA: " + character.getMana()
            );

            detail.setFont(AppTheme.NORMAL_FONT);
            detail.setForeground(AppTheme.WHITE);

            kartu.add(nama, BorderLayout.NORTH);
            kartu.add(detail, BorderLayout.CENTER);

            kartu.setAlignmentX(Component.LEFT_ALIGNMENT);
            kartu.setMaximumSize(
                    new Dimension(Integer.MAX_VALUE, 85)
            );

            daftarPanel.add(kartu);
            daftarPanel.add(Box.createVerticalStrut(10));
        }

        statistikLabel.setText(
                "TOTAL KARAKTER: "
                + manager.getTotalCharacters()
                + "     |     RATA-RATA LEVEL: "
                + String.format(
                        "%.2f",
                        manager.getAverageLevel()
                )
        );

        daftarPanel.revalidate();
        daftarPanel.repaint();
    }
}
