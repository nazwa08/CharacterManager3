
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

    private JPanel daftarPanel;
    private JLabel statistikLabel;
    private JLabel statusLabel;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager - TP12 v0.5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout utama
        setLayout(new BorderLayout(12, 12));

        JPanel header = buatHeader();
        JScrollPane scroll = buatDaftarKarakter();
        JPanel legenda = buatLegenda();
        JPanel statusBar = buatStatusBar();

        add(header, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(legenda, BorderLayout.EAST);
        add(statusBar, BorderLayout.SOUTH);

        getContentPane().setBackground(AppTheme.BACKGROUND);

        setMinimumSize(new Dimension(650, 450));
        pack();
        setLocationRelativeTo(null);

        refreshData();
    }

    // HEADER DAN PANEL STATISTIK
    private JPanel buatHeader() {

        JPanel header = new JPanel(new BorderLayout(8, 10));
        header.setBackground(AppTheme.BACKGROUND);
        header.setBorder(new EmptyBorder(15, 15, 5, 15));

        JLabel judul = new JLabel("CHARACTER MANAGER");
        judul.setFont(AppTheme.TITLE_FONT);
        judul.setForeground(AppTheme.TEXT);

        JLabel subjudul = new JLabel(
                "Kelola karakter dan lihat informasi setiap role."
        );
        subjudul.setFont(AppTheme.SUBTITLE_FONT);
        subjudul.setForeground(AppTheme.MUTED);

        JPanel teksHeader = new JPanel(new GridLayout(2, 1, 2, 2));
        teksHeader.setBackground(AppTheme.BACKGROUND);
        teksHeader.add(judul);
        teksHeader.add(subjudul);

        // Panel tambahan menggunakan GridLayout 2x2
        JPanel statistikPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        statistikPanel.setBackground(AppTheme.BACKGROUND);
        statistikPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        statistikLabel = new JLabel("Total karakter: 0");
        statistikLabel.setFont(AppTheme.BODY_FONT);
        statistikLabel.setForeground(AppTheme.TEXT);

        JLabel infoRole = new JLabel("Role: 4 jenis");
        infoRole.setFont(AppTheme.BODY_FONT);
        infoRole.setForeground(AppTheme.TEXT);

        JLabel infoLayout = new JLabel("Layout: BorderLayout");
        infoLayout.setFont(AppTheme.BODY_FONT);
        infoLayout.setForeground(AppTheme.TEXT);

        JLabel infoVersi = new JLabel("Sprint: 5 - v0.5");
        infoVersi.setFont(AppTheme.BODY_FONT);
        infoVersi.setForeground(AppTheme.TEXT);

        statistikPanel.add(statistikLabel);
        statistikPanel.add(infoRole);
        statistikPanel.add(infoLayout);
        statistikPanel.add(infoVersi);

        header.add(teksHeader, BorderLayout.NORTH);
        header.add(statistikPanel, BorderLayout.SOUTH);

        return header;
    }

    // DAFTAR KARAKTER DENGAN SCROLL
    private JScrollPane buatDaftarKarakter() {

        daftarPanel = new JPanel(new GridLayout(0, 2, 12, 12));
        daftarPanel.setBackground(AppTheme.BACKGROUND);
        daftarPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        // Panel pembungkus untuk grid kartu
        JPanel pembungkus = new JPanel(new BorderLayout());
        pembungkus.setBackground(AppTheme.BACKGROUND);

        // Grid kartu diletakkan pada NORTH panel pembungkus
        pembungkus.add(daftarPanel, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(
                pembungkus,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scroll.setPreferredSize(new Dimension(700, 420));
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(AppTheme.BACKGROUND);

        return scroll;
    }

    // KARTU KARAKTER
    private JPanel buatKartu(Character karakter) {

        JPanel kartu = new JPanel(new BorderLayout(8, 8));

        Color warnaRole = AppTheme.colorFor(karakter.getType());

        kartu.setBackground(AppTheme.PANEL);
        kartu.setOpaque(true);
        kartu.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(
                                0, 5, 0, 0, warnaRole
                        ),
                        AppTheme.PANEL_BORDER
                )
        );

        JLabel nama = new JLabel(karakter.getName());
        nama.setFont(AppTheme.CARD_TITLE_FONT);
        nama.setForeground(AppTheme.TEXT);

        JLabel tipe = new JLabel(
                "Role: " + karakter.getType().name()
        );
        tipe.setFont(AppTheme.BODY_FONT);
        tipe.setForeground(warnaRole);

        JLabel level = new JLabel(
                "Level: " + karakter.getLevel()
        );
        level.setFont(AppTheme.BODY_FONT);
        level.setForeground(AppTheme.MUTED);

        JPanel informasi = new JPanel(new GridLayout(2, 1, 3, 3));
        informasi.setBackground(AppTheme.PANEL);

        informasi.add(tipe);
        informasi.add(level);

        kartu.add(nama, BorderLayout.NORTH);
        kartu.add(informasi, BorderLayout.CENTER);

        kartu.setPreferredSize(new Dimension(230, 105));

        return kartu;
    }

    // LEGENDA DINAMIS
    private JPanel buatLegenda() {

        JPanel legenda = new JPanel();

        // BoxLayout menggunakan container legenda sendiri
        legenda.setLayout(
                new BoxLayout(legenda, BoxLayout.Y_AXIS)
        );

        legenda.setBackground(AppTheme.PANEL);
        legenda.setBorder(
                BorderFactory.createCompoundBorder(
                        AppTheme.PANEL_BORDER,
                        new EmptyBorder(5, 5, 5, 5)
                )
        );

        JLabel judul = new JLabel("LEGENDA ROLE");
        judul.setFont(AppTheme.CARD_TITLE_FONT);
        judul.setForeground(AppTheme.TEXT);
        judul.setAlignmentX(Component.LEFT_ALIGNMENT);

        legenda.add(judul);
        legenda.add(Box.createVerticalStrut(15));

        // Mengambil semua role dari enum
        for (CharacterType type : CharacterType.values()) {

            JPanel item = new JPanel(new FlowLayout(
                    FlowLayout.LEFT, 8, 5
            ));

            item.setBackground(AppTheme.PANEL);
            item.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel warna = new JLabel("   ");
            warna.setOpaque(true);
            warna.setBackground(AppTheme.colorFor(type));

            JLabel namaRole = new JLabel(type.name());
            namaRole.setFont(AppTheme.BODY_FONT);
            namaRole.setForeground(AppTheme.TEXT);

            item.add(warna);
            item.add(namaRole);

            legenda.add(item);
            legenda.add(Box.createVerticalStrut(8));
        }

        legenda.add(Box.createVerticalGlue());

        return legenda;
    }

    // STATUS BAR
    private JPanel buatStatusBar() {

        JPanel statusBar = new JPanel(new BorderLayout());

        statusBar.setBackground(AppTheme.PANEL);
        statusBar.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(
                                1, 0, 0, 0, AppTheme.BORDER_COLOR
                        ),
                        new EmptyBorder(10, 15, 10, 15)
                )
        );

        statusLabel = new JLabel("Character Manager siap digunakan.");
        statusLabel.setFont(AppTheme.SMALL_FONT);
        statusLabel.setForeground(AppTheme.MUTED);

        JLabel versi = new JLabel("v0.5");
        versi.setFont(AppTheme.SMALL_FONT);
        versi.setForeground(AppTheme.ACCENT);

        // Kiri untuk status, kanan untuk versi
        statusBar.add(statusLabel, BorderLayout.WEST);
        statusBar.add(versi, BorderLayout.EAST);

        return statusBar;
    }

    // MEMPERBARUI DAFTAR KARAKTER
    public void refreshData() {

        daftarPanel.removeAll();

        List<Character> daftar = manager.getAllCharacters();

        for (Character karakter : daftar) {
            daftarPanel.add(buatKartu(karakter));
        }

        statistikLabel.setText(
                "Total karakter: " + daftar.size()
        );

        statusLabel.setText(
                "Menampilkan " + daftar.size() + " karakter."
        );

        daftarPanel.revalidate();
        daftarPanel.repaint();

        revalidate();
        repaint();
    }
}
