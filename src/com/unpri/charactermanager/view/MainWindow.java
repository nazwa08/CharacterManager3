
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class MainWindow extends JFrame {

    private final CharacterManager manager;

    private final JPanel daftarPanel;
    private final JLabel statistikLabel;

    private final JTextField namaField;
    private final JTextField levelField;
    private final JComboBox<CharacterType> tipeComboBox;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager - TP8");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelUtama = new JPanel(new BorderLayout(10, 10));
        panelUtama.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelUtama.setBackground(new Color(245, 247, 250));

        // JUDUL
        JLabel judul = new JLabel("CHARACTER MANAGER");
        judul.setFont(new Font("Arial", Font.BOLD, 24));
        judul.setForeground(new Color(40, 55, 80));

        // FORM TAMBAH KARAKTER
        JPanel formPanel = new JPanel(new GridLayout(2, 4, 8, 8));
        formPanel.setBorder(BorderFactory.createTitledBorder(
                "Tambah Karakter Baru"));
        formPanel.setBackground(Color.WHITE);

        namaField = new JTextField();

        tipeComboBox = new JComboBox<>(CharacterType.values());

        levelField = new JTextField("1");

        JButton tambahButton = new JButton("Tambah Karakter");
        tambahButton.setBackground(new Color(40, 120, 80));
        tambahButton.setForeground(Color.WHITE);
        tambahButton.setFocusPainted(false);

        formPanel.add(new JLabel("Nama Karakter"));
        formPanel.add(new JLabel("Tipe Karakter"));
        formPanel.add(new JLabel("Level"));
        formPanel.add(new JLabel("Aksi"));

        formPanel.add(namaField);
        formPanel.add(tipeComboBox);
        formPanel.add(levelField);
        formPanel.add(tambahButton);

        // PANEL ATAS
        JPanel panelAtas = new JPanel(new BorderLayout(10, 10));
        panelAtas.setBackground(new Color(245, 247, 250));
        panelAtas.add(judul, BorderLayout.NORTH);
        panelAtas.add(formPanel, BorderLayout.CENTER);

        panelUtama.add(panelAtas, BorderLayout.NORTH);

        // DAFTAR KARAKTER
        daftarPanel = new JPanel();
        daftarPanel.setLayout(
                new BoxLayout(daftarPanel, BoxLayout.Y_AXIS));
        daftarPanel.setBackground(new Color(245, 247, 250));

        JScrollPane scrollPane = new JScrollPane(daftarPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);

        panelUtama.add(scrollPane, BorderLayout.CENTER);

        // STATISTIK
        statistikLabel = new JLabel();
        statistikLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statistikLabel.setForeground(new Color(25, 100, 70));
        statistikLabel.setBorder(new EmptyBorder(10, 5, 5, 5));

        panelUtama.add(statistikLabel, BorderLayout.SOUTH);

        add(panelUtama);

        // AKSI TOMBOL TAMBAH
        tambahButton.addActionListener(e -> tambahKarakter());

        tampilkanKarakter();

        setMinimumSize(new Dimension(750, 500));
        pack();
        setLocationRelativeTo(null);
    }

    // METHOD TAMBAH KARAKTER
    private void tambahKarakter() {

        String nama = namaField.getText().trim();
        String levelText = levelField.getText().trim();

        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nama karakter tidak boleh kosong!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );
            namaField.requestFocus();
            return;
        }

        if (manager.findByName(nama) != null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nama karakter sudah terdaftar!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );
            namaField.requestFocus();
            return;
        }

        int level;

        try {
            level = Integer.parseInt(levelText);

            if (level < 1) {
                JOptionPane.showMessageDialog(
                        this,
                        "Level minimal adalah 1!",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Level harus berupa angka!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );
            levelField.requestFocus();
            return;
        }

        CharacterType tipe =
                (CharacterType) tipeComboBox.getSelectedItem();

        Character karakter = new Character(nama, tipe, level);

        manager.addCharacter(karakter);

        JOptionPane.showMessageDialog(
                this,
                "Karakter berhasil ditambahkan!",
                "Berhasil",
                JOptionPane.INFORMATION_MESSAGE
        );

        namaField.setText("");
        levelField.setText("1");
        tipeComboBox.setSelectedIndex(0);

        tampilkanKarakter();
    }

    // METHOD MENAMPILKAN DAFTAR KARAKTER
    public void tampilkanKarakter() {

        daftarPanel.removeAll();

        List<Character> daftar = manager.getAllCharacters();

        for (Character karakter : daftar) {

            JPanel kartu = new JPanel(new BorderLayout(10, 5));

            kartu.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                            new Color(210, 215, 225)),
                    new EmptyBorder(12, 12, 12, 12)
            ));

            kartu.setBackground(Color.WHITE);
            kartu.setOpaque(true);

            JLabel nama = new JLabel(karakter.getName());
            nama.setFont(new Font("Arial", Font.BOLD, 17));

            JLabel informasi = new JLabel(
                    "Tipe: " + karakter.getType()
                    + " | Level: " + karakter.getLevel()
                    + " | HP: " + karakter.getHitPoints()
                    + " | Attack: " + karakter.getAttack()
                    + " | Mana: " + karakter.getMana()
            );

            informasi.setFont(new Font("Arial", Font.PLAIN, 13));

            kartu.add(nama, BorderLayout.NORTH);
            kartu.add(informasi, BorderLayout.CENTER);

            kartu.setMaximumSize(
                    new Dimension(Integer.MAX_VALUE, 85));

            kartu.setAlignmentX(Component.LEFT_ALIGNMENT);

            daftarPanel.add(kartu);
            daftarPanel.add(Box.createVerticalStrut(10));
        }

        double rataRata = manager.getAverageLevel();

        statistikLabel.setText(String.format(
                "Rata-rata Level: %.2f | Total Karakter: %d",
                rataRata,
                manager.getTotalCharacters()
        ));

        daftarPanel.revalidate();
        daftarPanel.repaint();
    }
}
