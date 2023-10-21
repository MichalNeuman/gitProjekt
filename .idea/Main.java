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
                System.out.println(kontynuuj);
                break;
            }
            System.out.println("Menu:");
            System.out.println("1. Wprowadź tekst ręcznie z klawiatury");
            System.out.println("2. Wprowadź tekst z pliku");
            System.out.println("3. Pobierz zawartość URL");
            System.out.println("4. Wyjdź");

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

                    } catch (FileNotFoundException e) {
                        System.out.println("Plik nie istnieje: " + e.getMessage());
                    } catch (IOException e) {
                        System.out.println("Błąd podczas czytania pliku: " + e.getMessage());
                    }
                    kontynuuj = false;
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

        // Wypisz ilość wystąpień
        for (int i = 0; i < listaZnakow.size(); i++) {
            if (listaWystepowania.get(i) != 0) {
                System.out.println(listaZnakow.get(i) + ": " + listaWystepowania.get(i));
            }
        }
    }
}