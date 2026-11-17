
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.ParseException;

public class CharacterFormPanel extends JPanel {

    private final CharacterManager manager;
    private final Runnable refreshCallback;

    private final JTextField namaField;
    private final JComboBox<CharacterType> roleCombo;
    private final JSpinner levelSpinner;
    private final JLabel pesanLabel;

    private final Border borderNamaAsli;
    private final Border borderRoleAsli;
    private final Border borderLevelAsli;

    public CharacterFormPanel(
            CharacterManager manager,
            Runnable refreshCallback) {

        this.manager = manager;
        this.refreshCallback = refreshCallback;

        setLayout(new GridLayout(0, 2, 10, 12));
        setBackground(AppTheme.FORM_BACKGROUND);
        setOpaque(true);
        setBorder(AppTheme.FORM_BORDER);

        namaField = new JTextField(15);
        roleCombo = new JComboBox<>(CharacterType.values());

        levelSpinner = new JSpinner(
                new SpinnerNumberModel(1, 1, 99, 1)
        );

        borderNamaAsli = AppTheme.INPUT_BORDER;
        borderRoleAsli = AppTheme.INPUT_BORDER;
        borderLevelAsli = AppTheme.INPUT_BORDER;

        namaField.setBorder(borderNamaAsli);
        roleCombo.setBorder(borderRoleAsli);
        levelSpinner.setBorder(borderLevelAsli);

        namaField.setBackground(AppTheme.INPUT_BACKGROUND);
        namaField.setForeground(AppTheme.WHITE);
        namaField.setCaretColor(AppTheme.WHITE);

        roleCombo.setBackground(AppTheme.INPUT_BACKGROUND);
        roleCombo.setForeground(AppTheme.WHITE);

        roleCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {

                super.getListCellRendererComponent(
                        list, value, index,
                        isSelected, cellHasFocus
                );

                setBackground(isSelected
                        ? AppTheme.PRIMARY
                        : AppTheme.INPUT_BACKGROUND);

                setForeground(isSelected
                        ? AppTheme.BACKGROUND
                        : AppTheme.WHITE);

                return this;
            }
        });

        levelSpinner.setBackground(AppTheme.INPUT_BACKGROUND);
        levelSpinner.setForeground(AppTheme.WHITE);

        JComponent editor = levelSpinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JFormattedTextField field =
                    ((JSpinner.DefaultEditor) editor).getTextField();

            field.setBackground(AppTheme.INPUT_BACKGROUND);
            field.setForeground(AppTheme.WHITE);
            field.setCaretColor(AppTheme.WHITE);
            field.setBorder(AppTheme.INPUT_BORDER);
        }

        namaField.setFont(AppTheme.NORMAL_FONT);
        roleCombo.setFont(AppTheme.NORMAL_FONT);
        levelSpinner.setFont(AppTheme.NORMAL_FONT);

        pesanLabel = new JLabel(" ");
        pesanLabel.setForeground(AppTheme.TEXT);
        pesanLabel.setFont(AppTheme.NORMAL_FONT);

        JButton tambahButton =
                new JButton("TAMBAH KARAKTER");

        tambahButton.setBackground(AppTheme.PRIMARY);
        tambahButton.setForeground(AppTheme.BACKGROUND);
        tambahButton.setFont(AppTheme.BUTTON_FONT);
        tambahButton.setFocusPainted(false);
        tambahButton.setOpaque(true);
        tambahButton.setBorderPainted(false);
        tambahButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        tambahButton.addActionListener(
                e -> tambahKarakter()
        );

        tambahLabel("Nama Karakter:");
        add(namaField);

        tambahLabel("Role:");
        add(roleCombo);

        tambahLabel("Level (1-99):");
        add(levelSpinner);

        tambahLabel("Pesan:");
        add(pesanLabel);

        add(new JLabel());
        add(tambahButton);
    }

    private void tambahLabel(String tulisan) {
        JLabel label = new JLabel(tulisan);
        label.setForeground(AppTheme.TEXT);
        label.setFont(AppTheme.NORMAL_FONT);
        add(label);
    }

    private String validateName(String name) {

        if (name == null || name.trim().isEmpty()) {
            return "Nama karakter wajib diisi.";
        }

        name = name.trim();

        if (name.length() > 30) {
            return "Nama maksimal 30 karakter.";
        }

        if (name.contains("  ")) {
            return "Nama tidak boleh mengandung spasi ganda.";
        }

        if (!name.matches("[\\p{L}]+(?:[ '-][\\p{L}]+)*")) {
            return "Nama hanya boleh berisi huruf dan pemisah kata.";
        }

        if (manager.isNameTaken(name)) {
            return "Nama karakter sudah digunakan.";
        }

        return null;
    }

    private void tambahKarakter() {

        String nama = namaField.getText().trim();

        resetError();

        String error = validateName(nama);

        if (error != null) {
            tampilkanError(error, namaField);
            return;
        }

        int level;

        try {
            levelSpinner.commitEdit();
            level = (Integer) levelSpinner.getValue();

        } catch (ParseException ex) {
            tampilkanError(
                    "Level harus berupa angka yang valid.",
                    levelSpinner
            );
            return;
        }

        CharacterType role =
                (CharacterType) roleCombo.getSelectedItem();

        try {
            Character character =
                    new Character(nama, role, level);

            manager.addCharacter(character);

            pesanLabel.setForeground(AppTheme.SUCCESS);
            pesanLabel.setText(
                    "Karakter berhasil ditambahkan!"
            );

            namaField.setText("");
            levelSpinner.setValue(1);
            roleCombo.setSelectedIndex(0);

            namaField.requestFocusInWindow();

            refreshCallback.run();

        } catch (IllegalArgumentException ex) {
            tampilkanError(ex.getMessage(), levelSpinner);
        }
    }

    private void tampilkanError(
            String pesan,
            JComponent komponen) {

        pesanLabel.setForeground(AppTheme.ERROR);
        pesanLabel.setText(pesan);

        if (komponen != null) {
            komponen.setBorder(AppTheme.ERROR_BORDER);
            komponen.requestFocusInWindow();
        }
    }

    private void resetError() {

        namaField.setBorder(borderNamaAsli);
        roleCombo.setBorder(borderRoleAsli);
        levelSpinner.setBorder(borderLevelAsli);

        pesanLabel.setForeground(AppTheme.TEXT);
        pesanLabel.setText(" ");
    }
}
