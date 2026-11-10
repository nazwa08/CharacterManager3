
package com.unpri.charactermanager.model;

public class Character {

    // Atribut private
    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attack;
    private int mana;

    // Constructor pertama
    public Character(String name, CharacterType type) {
        this.name = name;
        this.type = type;

        this.level = 1;
        this.hitPoints = 100;
        this.attack = 10;
        this.mana = 50;

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Nama karakter tidak boleh kosong."
            );
        }

        if (type == null) {
            throw new IllegalArgumentException(
                "Tipe karakter tidak boleh null."
            );
        }
    }

    // Constructor kedua
    public Character(String name, CharacterType type, int level) {
        this(name, type);
        setLevel(level);
    }

    // Getter dan setter nama
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Nama karakter tidak boleh kosong."
            );
        }

        this.name = name;
    }

    // Getter dan setter tipe
    public CharacterType getType() {
        return type;
    }

    public void setType(CharacterType type) {
        if (type == null) {
            throw new IllegalArgumentException(
                "Tipe karakter tidak boleh null."
            );
        }

        this.type = type;
    }

    // Getter dan setter level
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level < 1) {
            throw new IllegalArgumentException(
                "Level minimal adalah 1."
            );
        }

        this.level = level;

        // HP dan attack dihitung ulang berdasarkan level
        this.hitPoints = 100 + (level - 1) * 20;
        this.attack = 10 + (level - 1) * 5;
    }

    // Getter dan setter hitPoints
    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        if (hitPoints < 0) {
            throw new IllegalArgumentException(
                "Hit Points tidak boleh negatif."
            );
        }

        this.hitPoints = hitPoints;
    }

    // Getter dan setter attack
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        if (attack < 0) {
            throw new IllegalArgumentException(
                "Attack tidak boleh negatif."
            );
        }

        this.attack = attack;
    }

    // BAGIAN B: Getter mana
    public int getMana() {
        return mana;
    }

    // BAGIAN B: Setter mana dengan validasi
    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException(
                "Mana tidak boleh negatif."
            );
        }

        this.mana = mana;
    }

    // BAGIAN B: Mengecek apakah karakter masih hidup
    public boolean isAlive() {
        return hitPoints > 0;
    }

    // Menampilkan informasi karakter
    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", level=" + level +
                ", hitPoints=" + hitPoints +
                ", attack=" + attack +
                ", mana=" + mana +
                '}';
    }
}
