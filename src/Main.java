
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class Main {

    public static void main(String[] args) {

        // Membuat tiga karakter
        Character arthas = new Character(
            "Arthas", CharacterType.WARRIOR
        );

        Character merlin = new Character(
            "Merlin", CharacterType.MAGE, 5
        );

        Character robin = new Character(
            "Robin", CharacterType.ARCHER, 3
        );

        // Menampilkan tiga karakter
        System.out.println("=== DAFTAR KARAKTER ===");

        System.out.println(arthas);
        System.out.println(merlin);
        System.out.println(robin);

        // Demo level up Arthas
        System.out.println("\n=== DEMO LEVEL UP ===");

        arthas.setLevel(arthas.getLevel() + 1);

        System.out.println("Arthas naik level!");
        System.out.println(arthas);

        // Mini-Challenge: membuat karakter baru
        System.out.println("\n=== MINI CHALLENGE ===");

        Character luna = new Character(
            "Luna", CharacterType.MAGE, 3
        );

        // Mengatur mana
        luna.setMana(150);

        // Memanggil isAlive()
        System.out.println("Karakter baru: " + luna);
        System.out.println("Mana Luna: " + luna.getMana());
        System.out.println("Apakah Luna masih hidup? "
                + luna.isAlive());

        // Menguji validasi mana negatif
        System.out.println("\n=== UJI VALIDASI MANA ===");

        try {
            luna.setMana(-10);
        } catch (IllegalArgumentException e) {
            System.out.println("Validasi berhasil: "
                    + e.getMessage());
        }

        // Menguji isAlive ketika HP nol
        luna.setHitPoints(0);

        System.out.println("HP Luna: " + luna.getHitPoints());
        System.out.println("Apakah Luna masih hidup? "
                + luna.isAlive());
    }
}
