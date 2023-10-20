import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String tekst = "ala ma kota";
        /*
        do {
            System.out.print("Wczytać z plików (P) czy wprowadzić tekst ręcznie (R)? ");
            String opcja = scanner.next().toLowerCase();

            if (opcja.equals("p")) {
                System.out.print("Podaj ścieżkę do katalogu z plikami tekstowymi: ");
                String katalog = scanner.next();
//NAPISAĆ METODĘ SPRAWDZAJĄCA CZY KATALOG I PLIKI (*.txt) ISTNIEJĄ
//JEŚLI TAK TO ZCZYTUJEMY Z NICH TEKST I WYSYŁAMY DO ????utworzListyCharInt(tekst)?????
                break;
            } else if (opcja.equals("r")) {
                System.out.print("Wprowadź tekst: ");
                tekst = scanner.next().toLowerCase();
                break;
            } else {
                System.out.println("Nieprawidłowa opcja. Wybierz 'P' lub 'R'.");
            }
        } while (true);
*/

        List<Character> listaZnakow = new ArrayList<>();
        List<Integer> listaWystepowania = new ArrayList<>();

        //Sprawdzanie powtarzalności i zapisywanie o jaki znak chodzi do listy listaZnakow oraz ile razy występuje w liście listaWystapien
        for (char litera = 'a'; litera <= 'z'; litera++) {
            int counter = 0;
            for (char znak : tekst.toCharArray()) {
                if (znak == litera) {
                    counter++;
                }
            }
            listaZnakow.add(litera);
            listaWystepowania.add(counter);
        }

        for (int i = 0; i < listaZnakow.size(); i++) {
            System.out.println(listaZnakow.get(i) + ": " + listaWystepowania.get(i));
        }
    }
}
