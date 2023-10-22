import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String tekst = null;
        StringBuilder stringBuilder = new StringBuilder();
        boolean kontynuuj = true;


        while (kontynuuj){
            if(kontynuuj == false){
                break;
            }
            System.out.println("Menu:");
            System.out.println("1. Wprowadź tekst ręcznie z klawiatury");
            System.out.println("2. Wprowadź tekst z pliku");
            System.out.println("3. Pobierz zawartość URL");
            System.out.println("4. Wyjdź");
            System.out.flush();

            int wybor = scanner.nextInt();
            scanner.nextLine(); // Konsumuj znak nowej linii

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
        System.out.flush();

        //Sprawdzanie powtarzalności i zapisywanie o jaki znak chodzi do listy listaZnakow oraz ile razy występuje w liście listaWystapien
        ArrayList<Character> listaZnakow = new ArrayList<>();
        ArrayList<Integer> listaWystepowania = new ArrayList<>();

        for (char litera = 'a'; litera <= 'z'; litera++) {
            String tekstLower = tekst.toLowerCase();
            int counter = 0;
            for (char znak : tekstLower.toCharArray()) {
                if (znak == litera) { counter++; }
            }
            if(counter != 0){
                listaZnakow.add(litera);
                listaWystepowania.add(counter);
            }
        }
        //Wypisanie ile razy co występuje
        for (int i = 0; i < listaZnakow.size(); i++) {
            System.out.println(listaZnakow.get(i) + ": " + listaWystepowania.get(i));
        }

        kontynuuj = true;
        while (kontynuuj){
            if(kontynuuj == false){
                break;
            }
            System.out.println("Menu:");
            System.out.println("1. Drukuj histogram do konsoli");
            System.out.println("2. Drukuj histogram do pliku");
            System.out.println("3. Wyjdź");
            System.out.flush();

            int wybor = scanner.nextInt(); // pobiera wybór użytkownika

            switch (wybor) {
                case 1:
                    GenerujHistogram(listaZnakow, listaWystepowania);
                    kontynuuj = false;
                    break;
                case 2:
                    System.out.print("Podaj nazwę pliku tekstowego: ");
                    String nazwaPliku = scanner.nextLine();

                    GenerujHistogram(listaZnakow, listaWystepowania, nazwaPliku);
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
    private static void GenerujHistogram(ArrayList<Character> listaZnakow, ArrayList<Integer> listaWystepowania) { // Generowanie histogramu w konsoli
        System.out.println("Histogram częstości występowania liter:");

        for (int i = 0; i < listaZnakow.size(); i++) {
            char litera = listaZnakow.get(i);
            int ilosc = listaWystepowania.get(i);
            System.out.println(litera + ": " + generujLinieHistogramu(ilosc, ObliczSkale(listaWystepowania)));
        }
    }
    private static void GenerujHistogram(ArrayList<Character> listaZnakow, ArrayList<Integer> listaWystepowania, String nazwaPliku) { // Generowanie histogramu do pliku txt
        try (PrintWriter writer = new PrintWriter(nazwaPliku)) {
            writer.println("Histogram częstości występowania liter:");

            for (int i = 0; i < listaZnakow.size(); i++) {
                char litera = listaZnakow.get(i);
                int ilosc = listaWystepowania.get(i);
                writer.println(litera + ": " + generujLinieHistogramu(ilosc, ObliczSkale(listaWystepowania)));
            }

            System.out.println("Histogram został zapisany do pliku " + nazwaPliku);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
    private static String generujLinieHistogramu(int ilosc, int skala) {
        StringBuilder histogram = new StringBuilder();
        for (int i = 0; i < (ilosc / skala); i++) {
            histogram.append("*");
        }
        return histogram.toString();
    }
    private static int ObliczSkale(ArrayList<Integer> listaWystepowania){ // Oblicza skale w jakiej się będzie wyświetlał histogram w przypadku otrzymania dużej liczby danych
        int skala = 1;
        if (Max(listaWystepowania) > 20) {
            skala = Max(listaWystepowania) / 20;
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