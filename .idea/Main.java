import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String tekst = null;
        String sprawdzTeZnaki = null;
        StringBuilder stringBuilder = new StringBuilder();
        boolean kontynuuj = true;

        while (kontynuuj) { // Zdefiniuj znaki do sprawdzenia
            if (kontynuuj == false) {
                break;
            }
            System.out.println("Menu:");
            System.out.println("1. Podaj Zestaw liter oddzielone przecinkami");
            System.out.println("2. Użyj zestawu wszystkich liter");
            System.out.println("3. Użyj zestawu wszystkich liter (razem z polskimi)");
            System.out.println("4. Wyjdź");
            System.out.print("");

            int wybor = scanner.nextInt();
            scanner.nextLine(); // Konsumuj znak nowej linii
            System.out.print("");

            switch (wybor){
                case 1:
                    System.out.print("Podaj Zestaw liter oddzielone przecinkami: ");
                    sprawdzTeZnaki = scanner.nextLine();
                    kontynuuj = false;
                    break;
                case 2:
                    sprawdzTeZnaki = "abcdefghijklmnopqrstuwxyz";
                    kontynuuj = false;
                    break;
                case 3:
                    sprawdzTeZnaki = "aąbcćdeęfghijklłmnńoópqrstuwxyzżź";
                    kontynuuj = false;
                    break;
                case 4:
                    System.out.println("Koniec programu.");
                    kontynuuj = false;
                    break;
            }
        }

        kontynuuj = true;
        while (kontynuuj){ // W jaki sposób pobrać tekst
            if(kontynuuj == false){
                break;
            }
            System.out.println("Menu:");
            System.out.println("1. Wprowadź tekst ręcznie z klawiatury");
            System.out.println("2. Wprowadź tekst z pliku");
            System.out.println("3. Pobierz zawartość URL");
            System.out.println("4. Wyjdź");
            System.out.print("");

            int wybor = scanner.nextInt();
            scanner.nextLine(); // Konsumuj znak nowej linii
            System.out.print("");

            switch (wybor) {
                case 1:
                    System.out.print("Wprowadź tekst: ");
                    tekst = scanner.nextLine();
                    kontynuuj = false;
                    break;
                case 2:
                    System.out.print("Podaj ścieżkę do pliku tekstowego: ");
                    String sciezkaPliku = scanner.nextLine();

                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(sciezkaPliku));
                        String liniaPliku;
                        while ((liniaPliku = reader.readLine()) != null) {
                            stringBuilder.append(liniaPliku).append("\n");
                        }
                        tekst = stringBuilder.toString();
                        kontynuuj = false;
                    } catch (FileNotFoundException e) {
                        System.out.println("Plik nie istnieje: " + e.getMessage());
                    } catch (IOException e) {
                        System.out.println("Błąd podczas czytania pliku: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Podaj adres URL: ");
                    String url = scanner.nextLine();
                    try {
                        URL website = new URL(url);
                        BufferedReader urlReader = new BufferedReader(new InputStreamReader(website.openStream()));
                        String line;
                        while ((line = urlReader.readLine()) != null) {
                            stringBuilder.append(line).append("\n");
                        }
                        tekst = stringBuilder.toString();
                        kontynuuj = false;
                    } catch (MalformedURLException e) {
                        System.err.println("Nieprawidłowy adres URL: " + e.getMessage());
                    } catch (IOException e) {
                        System.err.println("Błąd podczas pobierania zawartości strony: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Koniec programu.");
                    kontynuuj = false;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Wybierz 1, 2, 3 lub 4.");
            }
        }
        System.out.println();

        //DrukujCzestotliwosc(ZmianaDoChar(sprawdzTeZnaki), LicznikZnakow(tekst, ZmianaDoChar(sprawdzTeZnaki))); // Drukuje częstotliwość znaków

        kontynuuj = true;
        while (kontynuuj){ // Jak wydrukować histogram
            if(kontynuuj == false){
                break;
            }
            System.out.println("Menu:");
            System.out.println("1. Drukuj histogram do konsoli");
            System.out.println("2. Drukuj histogram do pliku");
            System.out.println("3. Wyjdź");

            int wybor = scanner.nextInt(); // pobiera wybór użytkownika
            System.out.print("");

            switch (wybor) {
                case 1:
                    GenerujHistogram(ZmianaDoChar(sprawdzTeZnaki), LicznikZnakow(tekst, ZmianaDoChar(sprawdzTeZnaki)));
                    kontynuuj = false;
                    break;
                case 2:
                    System.out.print("Podaj nazwę pliku tekstowego: ");
                    String nazwaPliku = scanner.nextLine();

                    GenerujHistogram(ZmianaDoChar(sprawdzTeZnaki), LicznikZnakow(tekst, ZmianaDoChar(sprawdzTeZnaki)), nazwaPliku);
                    /*
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(nazwaPliku));
                        String liniaPliku;
                        if ((liniaPliku = reader.readLine()) != null) {

                        }
                        kontynuuj = false;
                    } catch (FileNotFoundException e) {
                        System.out.println("Plik nie istnieje: " + e.getMessage());
                    } catch (IOException e) {
                        System.out.println("Błąd podczas czytania pliku: " + e.getMessage());
                    }
                     */
                    kontynuuj = false;
                    break;
                case 3:
                    System.out.println("Koniec programu.");
                    kontynuuj = false;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Wybierz 1, 2 lub 3.");
            }
        }
    }
    private static ArrayList<Integer> LicznikZnakow(String tekst, ArrayList<Character> listaZnakow){
        ArrayList<Integer> listaWystepowania = new ArrayList<>();

        for (char litera: listaZnakow) {
            int counter = 0;
            for (char znak : tekst.toCharArray()) {
                if (znak == litera) { counter++; }
            }
            listaWystepowania.add(counter);
        }
        return listaWystepowania;
    }
    private static void DrukujCzestotliwosc(ArrayList<Character> listaZnakow, ArrayList<Integer> listaWystepowania){
        for (int i = 0; i < listaZnakow.size(); i++) {
            if(listaWystepowania.get(i) != 0){
                System.out.println(listaZnakow.get(i) + ": " + listaWystepowania.get(i));
            }
        }
    }
    private static void GenerujHistogram(ArrayList<Character> listaZnakow, ArrayList<Integer> listaWystepowania) { // Generowanie histogramu w konsoli
        System.out.println("Histogram częstości występowania liter:");

        for (int i = 0; i < listaZnakow.size(); i++) {
            if(listaWystepowania.get(i) != 0){
                char litera = listaZnakow.get(i);
                int ilosc = listaWystepowania.get(i);
                System.out.println(litera + ": " + generujLinieHistogramu(ilosc, ObliczSkale(listaWystepowania)));
            }
        }
    }
    private static void GenerujHistogram(ArrayList<Character> listaZnakow, ArrayList<Integer> listaWystepowania, String nazwaPliku) { // Generowanie histogramu do pliku txt
        try (PrintWriter writer = new PrintWriter(nazwaPliku)) {
            writer.println("Histogram częstości występowania liter:");

            for (int i = 0; i < listaZnakow.size(); i++) {
                if(listaWystepowania.get(i) != 0) {
                    char litera = listaZnakow.get(i);
                    int ilosc = listaWystepowania.get(i);
                    writer.println(litera + ": " + generujLinieHistogramu(ilosc, ObliczSkale(listaWystepowania)));
                }
            }
            System.out.println("Histogram został zapisany do pliku " + nazwaPliku);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
    private static String generujLinieHistogramu(int ilosc, int skala) {
        StringBuilder histogram = new StringBuilder();
        for (int i = 0; i < (ilosc / skala); i++) {
            histogram.append("█");
        }
        return histogram.toString();
    }
    private static ArrayList<Character> ZmianaDoChar(String sprawdzTeZnaki){
        ArrayList<Character> listaZnakow = new ArrayList<>();

        for (char x: sprawdzTeZnaki.toLowerCase().toCharArray()) {
            if(x != ',' || x != ' '){
                listaZnakow.add(x);
            }
        }
        return listaZnakow;
    }
    private static int ObliczSkale(ArrayList<Integer> listaWystepowania){ // Oblicza skale w jakiej się będzie wyświetlał histogram w przypadku otrzymania dużej liczby danych
        int skala = 1;
        if (Max(listaWystepowania) > 50) {
            skala = Max(listaWystepowania) / 50;
        }
        return skala;
    }
    public static int Max(ArrayList<Integer> listaWystepowania) { //Zwraca największy element w Array
        int najwiekszy = listaWystepowania.get(0);

        for (int i = 0; i < listaWystepowania.size(); i++) {
            if (listaWystepowania.get(i) > najwiekszy) {
                najwiekszy = listaWystepowania.get(i);
            }
        }

        return najwiekszy; // Zwracamy znaleziony największy element.
    }
}