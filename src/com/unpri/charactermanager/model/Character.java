
package com.unpri.charactermanager.model;

import java.util.Objects;

public class Character {

    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attack;
    private int mana;

    public Character(String name, CharacterType type) {
        this(name, type, 1);
    }

    public Character(String name, CharacterType type, int level) {
        validateName(name);
        validateType(type);
        validateLevel(level);
        validateMageLevel(type, level);

        this.name = name.trim();
        this.type = type;
        this.level = level;

        updateStats();
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Nama karakter tidak boleh kosong."
            );
        }
    }

    private void validateType(CharacterType type) {
        if (type == null) {
            throw new IllegalArgumentException(
                "Tipe karakter tidak boleh null."
            );
        }
    }

    private void validateLevel(int level) {
        if (level < 1 || level > 99) {
            throw new IllegalArgumentException(
                "Level harus berada antara 1 sampai 99."
            );
        }
    }

    private void validateMageLevel(CharacterType type, int level) {
        if (type == CharacterType.MAGE && level > 60) {
            throw new IllegalArgumentException(
                "Karakter MAGE tidak boleh memiliki level lebih dari 60."
            );
        }
    }

    private void updateStats() {
        hitPoints = 100 + (level * 10);
        attack = 10 + (level * 5);
        mana = 50 + (level * 10);
    }

    public String getName() {
        return name;
    }

    public CharacterType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getAttack() {
        return attack;
    }

    public int getMana() {
        return mana;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name.trim();
    }

    public void setType(CharacterType type) {
        validateType(type);
        validateMageLevel(type, level);
        this.type = type;
    }

    public void setLevel(int level) {
        validateLevel(level);
        validateMageLevel(type, level);
        this.level = level;
        updateStats();
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException(
                "Mana tidak boleh negatif."
            );
        }
        this.mana = mana;
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Character)) return false;

        Character other = (Character) obj;
        return name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    @Override
    public String toString() {
        return name + " (" + type + ") - Level " + level
                + " | HP: " + hitPoints
                + " | Attack: " + attack
                + " | Mana: " + mana;
    }
}
