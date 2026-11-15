
package com.unpri.charactermanager.model;

import java.util.Objects;

public class Character {

    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attack;
    private int mana;

    private static int totalCharacterCreated = 0;

    public Character(String name, CharacterType type) {
        this(name, type, 1);
    }

    public Character(String name, CharacterType type, int level) {
        setName(name);
        setType(type);
        setLevel(level);
        this.mana = 50;
        totalCharacterCreated++;
    }

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
        this.hitPoints = 100 + (level * 20);
        this.attack = 10 + (level * 5);
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

    public static int getTotalCharacterCreated() {
        return totalCharacterCreated;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Character)) {
            return false;
        }

        Character other = (Character) obj;

        return name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

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
