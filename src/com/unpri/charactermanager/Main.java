
package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        CharacterManager manager = new CharacterManager();

        System.out.println("===== CHARACTER MANAGER TP5 =====");

        // 1. Membuat karakter
        Character c1 = new Character("Arthur", CharacterType.WARRIOR, 5);
        Character c2 = new Character("Merlin", CharacterType.MAGE, 3);
        Character c3 = new Character("Robin", CharacterType.ARCHER, 2);
        Character c4 = new Character("Lancelot", CharacterType.WARRIOR, 8);

        // 2. Menambahkan karakter
        System.out.println("\n--- TAMBAH KARAKTER ---");
        manager.addCharacter(c1);
        manager.addCharacter(c2);
        manager.addCharacter(c3);
        manager.addCharacter(c4);

        // 3. Menguji nama duplikat
        System.out.println("\n--- UJI DUPLIKAT ---");
        Character duplikat = new Character(
                "Arthur", CharacterType.MAGE, 4
        );
        manager.addCharacter(duplikat);

        // 4. Menampilkan semua karakter
        System.out.println("\n--- DAFTAR KARAKTER ---");
        for (Character c : manager.getAllCharacters()) {
            System.out.println(c);
        }

        // 5. Menguji pencarian
        System.out.println("\n--- CARI KARAKTER ---");
        Character ditemukan = manager.findByName("Merlin");

        if (ditemukan != null) {
            System.out.println("Karakter ditemukan: " + ditemukan);
        } else {
            System.out.println("Karakter tidak ditemukan.");
        }

        // 6. Menghitung total karakter
        System.out.println("\nTotal karakter: "
                + manager.getTotalCharacters());

        // 7. Menguji rata-rata level
        System.out.println("\n--- RATA-RATA LEVEL ---");
        System.out.println("Rata-rata level: "
                + manager.getAverageLevel());

        // Menguji rata-rata jika daftar kosong
        CharacterManager managerKosong = new CharacterManager();
        System.out.println("Rata-rata daftar kosong: "
                + managerKosong.getAverageLevel());

        // 8. Mencari karakter berdasarkan tipe
        System.out.println("\n--- KARAKTER WARRIOR ---");
        ArrayList<Character> warriors =
                manager.findByType(CharacterType.WARRIOR);

        for (Character c : warriors) {
            System.out.println(c);
        }

        // 9. Fitur tambahan Bagian C
        System.out.println("\n--- KARAKTER TERKUAT ---");
        Character strongest = manager.findStrongest();

        if (strongest != null) {
            System.out.println("Karakter terkuat: " + strongest);
        }

        // 10. Menguji defensive copy
        System.out.println("\n--- UJI DEFENSIVE COPY ---");

        ArrayList<Character> salinan = manager.getAllCharacters();

        System.out.println("Jumlah sebelum salinan diubah: "
                + manager.getTotalCharacters());

        salinan.clear();

        System.out.println("Jumlah data salinan setelah clear: "
                + salinan.size());

        System.out.println("Jumlah data asli setelah salinan diubah: "
                + manager.getTotalCharacters());

        // 11. Menguji reference
        System.out.println("\n--- UJI REFERENCE ---");

        Character a = new Character(
                "ReferenceHero", CharacterType.WARRIOR, 2
        );

        Character b = a;

        b.setLevel(8);

        System.out.println("Level a: " + a.getLevel());
        System.out.println("Level b: " + b.getLevel());
        System.out.println("a == b: " + (a == b));

        // Membuat objek independen
        Character independen = new Character(
                a.getName(), a.getType(), a.getLevel()
        );

        independen.setLevel(3);

        System.out.println("Level a setelah objek independen diubah: "
                + a.getLevel());

        System.out.println("Level objek independen: "
                + independen.getLevel());

        System.out.println("a == independen: "
                + (a == independen));

        // 12. Menguji equals
        System.out.println("\n--- UJI EQUALS ---");

        Character hero1 = new Character(
                "Gandalf", CharacterType.MAGE
        );

        Character hero2 = new Character(
                "gandalf", CharacterType.WARRIOR
        );

        System.out.println("hero1.equals(hero2): "
                + hero1.equals(hero2));

        // 13. Menghapus karakter
        System.out.println("\n--- HAPUS KARAKTER ---");
        manager.removeCharacter("Robin");

        System.out.println("Total setelah penghapusan: "
                + manager.getTotalCharacters());

        // 14. Total objek yang pernah dibuat
        System.out.println("\nTotal objek Character yang dibuat: "
                + Character.getTotalCharacterCreated());

        System.out.println("\n===== PROGRAM SELESAI =====");
    }
}
