import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String tekst = null;
        StringBuilder stringBuilder = new StringBuilder();

while(true){
    System.out.println("Menu");
    System.out.println("1. Wprowadź tekst ręcznie z klawiatury");
    System.out.println("2. Wprowadz tekst z pliku");
    System.out.println("3. Pobierz zawartosc URL");
    System.out.println("4. Wyjdź");
    int wybor = scanner.nextByte();

    switch (wybor) {
        case 1:
            System.out.print("Wprowadź tekst: ");
            tekst = scanner.next();
            break;
        case 2:
            System.out.print("Podaj ścieżkę do katalogu z plikami tekstowymi: ");
            String katalog = scanner.next();
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
            break;
            }
        case 3:
            break;//Pobierz zawartosc URL
        case 4:
            System.out.println("Koniec programu.");
            System.exit(0);

        default:
            System.out.println("Nieprawidłowy wybór. Wybierz 1, 2 lub 3.");
    }
}

        //Sprawdzanie powtarzalności i zapisywanie o jaki znak chodzi do listy listaZnakow oraz ile razy występuje w liście listaWystapien
        ArrayList<Character> listaZnakow = new ArrayList<>();
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
