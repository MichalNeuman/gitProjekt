import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.print("Wczytać z plików (P) czy wprowadzić tekst ręcznie (R)? ");
            String opcja = scanner.next().toLowerCase();

            if (opcja.equals("p")) {
                System.out.print("Podaj ścieżkę do katalogu z plikami tekstowymi: ");
                String katalog = scanner.next();
                Map<Character, Integer> zliczenieLiter = przetworzKatalog(katalog);
                wyswietlHistogram(zliczenieLiter);
                break;
            } else if (opcja.equals("r")) {
                System.out.print("Wprowadź tekst: ");
                String tekst = scanner.next();
                Map<Character, Integer> zliczenieLiter = zliczLiteryWTekscie(tekst);
                wyswietlHistogram(zliczenieLiter);
                break;
            } else {
                System.out.println("Nieprawidłowa opcja. Wybierz 'P' lub 'R'.");
            }
        }while(true);
    }

    public static Map<Character, Integer> zliczLiteryWTekscie(String tekst) {
        Map<Character, Integer> zliczenieLiter = new HashMap<>();
        for (char znak : tekst.toCharArray()) {
            if (Character.isLetter(znak)) {
                znak = Character.toLowerCase(znak);
                zliczenieLiter.put(znak, zliczenieLiter.getOrDefault(znak, 0) + 1);
            }
        }
        return zliczenieLiter;
    }

    public static Map<Character, Integer> przetworzKatalog(String sciezkaKatalogu) {
        Map<Character, Integer> zliczenieLiter = new HashMap<>();
        File katalog = new File(sciezkaKatalogu);
        if (!katalog.exists() || !katalog.isDirectory()) {
            System.out.println("Podany katalog nie istnieje.");
            return zliczenieLiter;
        }

        File[] pliki = katalog.listFiles();
        if (pliki != null) {
            for (File plik : pliki) {
                if (plik.isFile() && plik.getName().endsWith(".txt")) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(plik))) {
                        String linia;
                        while ((linia = reader.readLine()) != null) {
                            Map<Character, Integer> zliczenieLiterek = zliczLiteryWTekscie(linia);
                            zliczenieLiterek.forEach((litera, ilosc) -> zliczenieLiter.merge(litera, ilosc, Integer::sum));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return zliczenieLiter;
    }

    public static void wyswietlHistogram(Map<Character, Integer> zliczenieLiter) {
        System.out.println("Histogram częstości liter:");
        for (Map.Entry<Character, Integer> entry : zliczenieLiter.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}