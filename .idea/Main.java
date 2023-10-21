import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String tekst = null;
        StringBuilder stringBuilder = new StringBuilder();

        do {
            System.out.print("Wczytać z plików (P) czy wprowadzić tekst ręcznie (R)? ");
            String opcja = scanner.next().toLowerCase();

            if (opcja.equals("p")) {
                System.out.print("Podaj ścieżkę do katalogu z plikami tekstowymi: ");
                String katalog = scanner.next();
//NAPISAĆ METODĘ SPRAWDZAJĄCA CZY KATALOG I PLIKI (*.txt) ISTNIEJĄ
                try{
                    BufferedReader reader = new BufferedReader(new FileReader(katalog));
                    String liniaPliku = null;
                    while((liniaPliku = reader.readLine())!=null){
                        stringBuilder.append(liniaPliku);
                    }
                    tekst =stringBuilder.toString();
                    break;
                }catch (Exception e){
                    System.out.println(e + " plik nie istnieje");
                }
//JEŚLI TAK TO ZCZYTUJEMY Z NICH TEKST I WYSYŁAMY DO ????utworzListyCharInt(tekst)?????
                break;
            } else if (opcja.equals("r")) {
                System.out.print("Wprowadź tekst: ");
                tekst = scanner.next();
                break;
            } else {
                System.out.println("Nieprawidłowa opcja. Wybierz 'P' lub 'R'.");
            }
        } while (true);



        //Sprawdzanie powtarzalności i zapisywanie o jaki znak chodzi do listy listaZnakow oraz ile razy występuje w liście listaWystapien
        List<Character> listaZnakow = new ArrayList<>();
        List<Integer> listaWystepowania = new ArrayList<>();

        for (char litera = 'a'; litera <= 'z'; litera++) {
            int counter = 0;
            for (char znak : tekst.toLowerCase().toCharArray()) {
                if (znak == litera) {
                    counter++;
                }
            }
            listaZnakow.add(litera);
            listaWystepowania.add(counter);
        }
        //Wypisanie ile razy co występuje
        for (int i = 0; i < listaZnakow.size(); i++) {
            if(listaWystepowania.get(i) != 0) {
                System.out.println(listaZnakow.get(i) + ": " + listaWystepowania.get(i));
            }
        }
    }
}
