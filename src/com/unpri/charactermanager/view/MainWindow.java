
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainWindow extends JFrame {

    private final CharacterManager manager;

    private final JPanel daftarPanel;
    private final JLabel statistikLabel;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager - TP10");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelUtama = new JPanel(new BorderLayout(15, 15));
        panelUtama.setBackground(AppTheme.BACKGROUND);
        panelUtama.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // JUDUL
        JLabel judul = new JLabel(
                "CHARACTER MANAGER",
                SwingConstants.CENTER
        );

        judul.setFont(AppTheme.FONT_TITLE);
        judul.setForeground(AppTheme.GOLD);

        // STATISTIK
        statistikLabel = new JLabel();
        statistikLabel.setFont(AppTheme.FONT_BODY);
        statistikLabel.setForeground(AppTheme.TEXT_COLOR);

        JPanel panelAtas = new JPanel(new BorderLayout(10, 10));
        panelAtas.setBackground(AppTheme.BACKGROUND);
        panelAtas.add(judul, BorderLayout.NORTH);
        panelAtas.add(statistikLabel, BorderLayout.SOUTH);

        // DAFTAR KARAKTER
        daftarPanel = new JPanel(new GridLayout(0, 2, 12, 12));
        daftarPanel.setBackground(AppTheme.BACKGROUND);

        JScrollPane scrollPane = new JScrollPane(daftarPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(AppTheme.BACKGROUND);

        // TOMBOL TAMBAH
        JButton tombolTambah = new JButton("+ Tambah Karakter");
        tombolTambah.setFont(AppTheme.FONT_BODY);
        tombolTambah.setBackground(AppTheme.BUTTON_COLOR);
        tombolTambah.setForeground(AppTheme.TEXT_COLOR);
        tombolTambah.setFocusPainted(false);
        tombolTambah.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        tombolTambah.addActionListener(e -> tambahKarakter());

        JPanel panelBawah = new JPanel(
                new FlowLayout(FlowLayout.CENTER)
        );
        panelBawah.setBackground(AppTheme.BACKGROUND);
        panelBawah.add(tombolTambah);

        panelUtama.add(panelAtas, BorderLayout.NORTH);
        panelUtama.add(scrollPane, BorderLayout.CENTER);
        panelUtama.add(panelBawah, BorderLayout.SOUTH);

        add(panelUtama);

        tampilkanKarakter();

        // Mengatur ukuran otomatis sesuai komponen
        pack();
        setMinimumSize(new Dimension(600, 450));
        setLocationRelativeTo(null);
    }

    // MENAMPILKAN SEMUA KARAKTER
    private void tampilkanKarakter() {

        daftarPanel.removeAll();

        List<Character> daftar = manager.getAllCharacters();

        for (Character character : daftar) {

            JPanel kartu = new JPanel();
            kartu.setLayout(new BoxLayout(kartu, BoxLayout.Y_AXIS));

            // WARNA SESUAI ROLE
            kartu.setBackground(
                    AppTheme.colorFor(character.getType())
            );

            kartu.setOpaque(true);
            kartu.setBorder(AppTheme.CARD_BORDER);

            // NAMA KARAKTER
            JLabel labelNama = new JLabel(
                    "Nama: " + character.getName()
            );

            labelNama.setFont(AppTheme.FONT_SUBTITLE);
            labelNama.setForeground(AppTheme.TEXT_COLOR);

            // ROLE
            JLabel labelRole = new JLabel(
                    "Role: " + character.getType()
            );

            labelRole.setFont(AppTheme.FONT_BODY);
            labelRole.setForeground(AppTheme.TEXT_COLOR);

            // LEVEL
            JLabel labelLevel = new JLabel(
                    "Level: " + character.getLevel()
            );

            labelLevel.setFont(AppTheme.FONT_BODY);
            labelLevel.setForeground(AppTheme.TEXT_COLOR);

            // MINI-CHALLENGE LEVEL MINIMAL 5
            if (character.getLevel()
                    >= AppTheme.HIGH_LEVEL_THRESHOLD) {

                labelNama.setForeground(AppTheme.GOLD);

                labelNama.setFont(
                        AppTheme.FONT_SUBTITLE.deriveFont(Font.BOLD)
                );

                labelLevel.setForeground(AppTheme.GOLD);

                labelLevel.setFont(
                        AppTheme.FONT_BODY.deriveFont(Font.BOLD)
                );
            }

            // PROPERTI TAMBAHAN 1: TOOLTIP
            kartu.setToolTipText(
                    "HP: " + character.getHitPoints()
                    + " | Attack: " + character.getAttack()
            );

            // PROPERTI TAMBAHAN 2: HAND CURSOR
            kartu.setCursor(
                    Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
            );

            kartu.add(labelNama);
            kartu.add(Box.createVerticalStrut(8));
            kartu.add(labelRole);
            kartu.add(labelLevel);

            daftarPanel.add(kartu);
        }

        // STATISTIK
        statistikLabel.setText(
                "Total Karakter: " + manager.getTotalCharacters()
        );

        daftarPanel.revalidate();
        daftarPanel.repaint();
    }

    // FITUR TAMBAH KARAKTER
    private void tambahKarakter() {

        JTextField inputNama = new JTextField();

        JComboBox<CharacterType> inputRole =
                new JComboBox<>(CharacterType.values());

        JSpinner inputLevel = new JSpinner(
                new SpinnerNumberModel(1, 1, 100, 1)
        );

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));

        form.add(new JLabel("Nama Karakter:"));
        form.add(inputNama);

        form.add(new JLabel("Role:"));
        form.add(inputRole);

        form.add(new JLabel("Level:"));
        form.add(inputLevel);

        int hasil = JOptionPane.showConfirmDialog(
                this,
                form,
                "Tambah Karakter Baru",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (hasil == JOptionPane.OK_OPTION) {

            String nama = inputNama.getText().trim();

            CharacterType role =
                    (CharacterType) inputRole.getSelectedItem();

            int level = (Integer) inputLevel.getValue();

            if (nama.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Nama karakter tidak boleh kosong."
                );
                return;
            }

            if (manager.findByName(nama) != null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Nama karakter sudah digunakan."
                );
                return;
            }

            try {

                Character karakterBaru =
                        new Character(nama, role, level);

                manager.addCharacter(karakterBaru);

                tampilkanKarakter();

                JOptionPane.showMessageDialog(
                        this,
                        "Karakter berhasil ditambahkan!"
                );

            } catch (IllegalArgumentException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Kesalahan",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
