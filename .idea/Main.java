import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
                String tekst = scanner.next();

                break;
            } else {
                System.out.println("Nieprawidłowa opcja. Wybierz 'P' lub 'R'.");
            }
        } while (true);
    }

    //Zliczanie liter (ASCII 97 - 122)
    public static ArrayList<Object> utworzListyCharInt(String tekst) {
        List<Character> listaZnakow = new ArrayList<>();
        List<Integer> listaWystepowania = new ArrayList<>();

        //Sprawdzanie powtarzalności i zapisywanie o jaki znak chodzi do listy listaZnakow oraz ile razy występuje w liście listaWystapien
        for (int i = 97; i <= 122; i++) {
            int counter = 0;
            for (char znak: tekst.toCharArray()) {
                if(znak == i){
                    counter++;
                }
            }
            char znak = (char) i;
            listaZnakow.add(znak);
            listaWystepowania.add(counter);
        }
        List<Object> wynik = new ArrayList <Object>();
        wynik.add(listaZnakow);
        wynik.add(listaWystepowania);
        return (ArrayList<Object>) wynik;
    }

    //Wydrukuj histogram -------------------------
    public static void DrukujDaneHistogram(ArrayList<Object> wystepowanie){
        for (int i = 0; i < ((List<?>) wystepowanie.get(0)).size(); i++) {
            System.out.println(((List<?>) wystepowanie.get(0)).get(i) + ": " + (List<?>) ((List<?>) wystepowanie.get(1)).get(i));
        }
    }
}

