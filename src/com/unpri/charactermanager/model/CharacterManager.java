
package com.unpri.charactermanager.model;

import java.util.ArrayList;
import java.util.List;

public class CharacterManager {

    private final List<Character> characters = new ArrayList<>();

    public void addCharacter(Character character) {

        if (character == null) {
            throw new IllegalArgumentException(
                "Karakter tidak boleh null."
            );
        }

        if (isNameTaken(character.getName())) {
            throw new IllegalArgumentException(
                "Nama karakter sudah digunakan."
            );
        }

        if (characters.size() >= 20) {
            throw new IllegalArgumentException(
                "Maksimal hanya 20 karakter yang dapat ditambahkan."
            );
        }

        characters.add(character);
    }

    public boolean isNameTaken(String name) {
        if (name == null) return false;

        return characters.stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(name.trim()));
    }

    public Character findByName(String name) {
        if (name == null) return null;

        for (Character character : characters) {
            if (character.getName().equalsIgnoreCase(name.trim())) {
                return character;
            }
        }

        return null;
    }

    public boolean removeCharacter(String name) {
        Character character = findByName(name);

        if (character == null) return false;

        return characters.remove(character);
    }

    public int getTotalCharacters() {
        return characters.size();
    }

    public List<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }

    public double getAverageLevel() {
        if (characters.isEmpty()) return 0;

        int total = 0;

        for (Character character : characters) {
            total += character.getLevel();
        }

        return (double) total / characters.size();
    }

    public List<Character> findByType(CharacterType type) {
        List<Character> result = new ArrayList<>();

        for (Character character : characters) {
            if (character.getType() == type) {
                result.add(character);
            }
        }

        return result;
    }
}
