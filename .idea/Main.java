import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.net.*;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sCaNnEr = new Scanner(System.in);
        String tEkSt = null;
        String sPrAwDzTeZnAkI = null;
        StringBuilder sTrInGbUiLdEr = new StringBuilder();
        boolean kOnTyNuUj = true;

        while (kOnTyNuUj) { // Zdefiniuj znaki do sprawdzenia
            if (!kOnTyNuUj) {
                break;
            }
            System.out.println("Menu:");
            System.out.println("1. Podaj Zestaw liter oddzielone przecinkami");
            System.out.println("2. Użyj zestawu wszystkich liter");
            System.out.println("3. Użyj zestawu wszystkich liter (razem z polskimi)");
            System.out.println("4. Wyjdź");
            System.out.print("");

            int wYbOr = sCaNnEr.nextInt();
            sCaNnEr.nextLine(); // Konsumuj znak nowej linii
            System.out.print("");

            switch (wYbOr){
                case 1:
                    System.out.print("Podaj Zestaw liter oddzielone przecinkami: ");
                    sPrAwDzTeZnAkI = sCaNnEr.nextLine();
                    kOnTyNuUj = false;
                    break;
                case 2:
                    sPrAwDzTeZnAkI = "abcdefghijklmnopqrstuwxyz";
                    kOnTyNuUj = false;
                    break;
                case 3:
                    sPrAwDzTeZnAkI = "aąbcćdeęfghijklłmnńoópqrstuwxyzżź";
                    kOnTyNuUj = false;
                    break;
                case 4:
                    System.out.println("Koniec programu.");
                    kOnTyNuUj = false;
                    break;
            }
        }

        kOnTyNuUj = true;
        while (kOnTyNuUj){ // W jaki sposób pobrać tekst
            if(!kOnTyNuUj){
                break;
            }
            System.out.println("Menu:");
            System.out.println("1. Wprowadź tekst ręcznie z klawiatury");
            System.out.println("2. Wprowadź tekst z pliku");
            System.out.println("3. Wprowadż tekst z katalogu");
            System.out.println("4. Pobierz zawartość URL");
            System.out.println("5. Wyjdź");
            System.out.print("");

            int wYbOr = sCaNnEr.nextInt();
            sCaNnEr.nextLine(); // Konsumuj znak nowej linii
            System.out.print("");

            switch (wYbOr) {
                case 1:
                    System.out.print("Wprowadź tekst: ");
                    tEkSt = sCaNnEr.nextLine();
                    kOnTyNuUj = false;
                    break;
                case 2:
                    System.out.print("Podaj ścieżkę do pliku tekstowego: ");
                    String sCiEzKaPliKu = sCaNnEr.nextLine();

                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(sCiEzKaPliKu));
                        String lInIaPlIkU;
                        while ((lInIaPlIkU = reader.readLine()) != null) {
                            sTrInGbUiLdEr.append(lInIaPlIkU).append("\n");
                        }
                        tEkSt = sTrInGbUiLdEr.toString();
                        kOnTyNuUj = false;
                    } catch (FileNotFoundException e) {
                        System.out.println("Plik nie istnieje: " + e.getMessage());
                    } catch (IOException e) {
                        System.out.println("Błąd podczas czytania pliku: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Podaj sciezke do katalogu");
                    String sCiEzKaDoKaTaLoGu = sCaNnEr.nextLine();
                    File fOlDeR = new File(sCiEzKaDoKaTaLoGu);
                    File[] pLiKi = fOlDeR.listFiles();
                    if (pLiKi != null) {
                        for (File pLiK : pLiKi) {
                            if (pLiK.isFile()) {
                                System.out.println("Odczytywanie zawartości pliku: " + pLiK.getName());

                                try {
                                    Scanner sCaNnEr1 = new Scanner(pLiK);

                                    while (sCaNnEr1.hasNextLine()) {
                                        sTrInGbUiLdEr.append(sCaNnEr1.nextLine());
                                    }
                                    sCaNnEr1.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        tEkSt = sTrInGbUiLdEr.toString();
                        kOnTyNuUj = false;
                        break;
                    } else {
                        System.out.println("Katalog jest pusty lub nie istnieje.");
                    }
                    break;
                case 4:
                    System.out.print("Podaj adres URL: ");
                    String uRl = sCaNnEr.nextLine();
                    try {
                        URL wEbSiTe = new URL(uRl);
                        BufferedReader uRlReAdEr = new BufferedReader(new InputStreamReader(wEbSiTe.openStream()));
                        String lInE;
                        while ((lInE = uRlReAdEr.readLine()) != null) {
                            sTrInGbUiLdEr.append(lInE).append("\n");
                        }
                        tEkSt = sTrInGbUiLdEr.toString();
                        kOnTyNuUj = false;
                    } catch (MalformedURLException e) {
                        System.err.println("Nieprawidłowy adres URL: " + e.getMessage());
                    } catch (IOException e) {
                        System.err.println("Błąd podczas pobierania zawartości strony: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Koniec programu.");
                    kOnTyNuUj = false;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Wybierz 1, 2, 3 lub 4.");
            }
        }
        System.out.println();

        dRuKuJcZeStOtLiWoSc(zMiAnAdOcHaR(sPrAwDzTeZnAkI), lIcZnIkZnAkOw(tEkSt, zMiAnAdOcHaR(sPrAwDzTeZnAkI))); // Drukuje częstotliwość znaków

        kOnTyNuUj = true;
        while (kOnTyNuUj){ // Jak wydrukować histogram
            if(!kOnTyNuUj){
                break;
            }
            System.out.println("Menu:");
            System.out.println("1. Drukuj histogram do konsoli");
            System.out.println("2. Drukuj histogram do pliku");
            System.out.println("3. Wyjdź");

            int wYbOr = sCaNnEr.nextInt(); // pobiera wybór użytkownika
            System.out.print("");

            switch (wYbOr) {
                case 1:
                    gEnErUjHiStOgRaM(zMiAnAdOcHaR(sPrAwDzTeZnAkI), lIcZnIkZnAkOw(tEkSt, zMiAnAdOcHaR(sPrAwDzTeZnAkI)));
                    kOnTyNuUj = false;
                    break;
                case 2:
                    System.out.print("Podaj nazwę pliku tekstowego: ");
                    String nAzWaPlIkU = sCaNnEr.nextLine();

                    gEnErUjHiStOgRaM(zMiAnAdOcHaR(sPrAwDzTeZnAkI), lIcZnIkZnAkOw(tEkSt, zMiAnAdOcHaR(sPrAwDzTeZnAkI)), nAzWaPlIkU);
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
                    kOnTyNuUj = false;
                    break;
                case 3:
                    System.out.println("Koniec programu.");
                    kOnTyNuUj = false;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Wybierz 1, 2 lub 3.");
            }
        }
    }
    private static ArrayList<Integer> lIcZnIkZnAkOw(String tEkSt, ArrayList<Character> lIsTaZnAkOw){
        ArrayList<Integer> lIsTaWyStEpOwAnIa = new ArrayList<>();

        for (char lItErA: lIsTaZnAkOw) {
            int cOuNtEr = 0;
            for (char zNaK : tEkSt.toCharArray()) {
                if (zNaK == lItErA) { cOuNtEr++; }
            }
            lIsTaWyStEpOwAnIa.add(cOuNtEr);
        }
        return lIsTaWyStEpOwAnIa;
    }
    private static void dRuKuJcZeStOtLiWoSc(ArrayList<Character> lIsTaZnAkOw, ArrayList<Integer> liStAwYsTePoWaNiA){
        for (int i = 0; i < lIsTaZnAkOw.size(); i++) {
            if(liStAwYsTePoWaNiA.get(i) != 0){
                System.out.println(lIsTaZnAkOw.get(i) + ": " + liStAwYsTePoWaNiA.get(i));
            }
        }
    }
    private static void gEnErUjHiStOgRaM(ArrayList<Character> lIsTaZnAkOw, ArrayList<Integer> lIsTaWyStEpOwAnIa) { // Generowanie histogramu w konsoli
        System.out.println("Histogram częstości występowania liter:");

        for (int i = 0; i < lIsTaZnAkOw.size(); i++) {
            if(lIsTaWyStEpOwAnIa.get(i) != 0){
                char lItErA = lIsTaZnAkOw.get(i);
                int iLoSc = lIsTaWyStEpOwAnIa.get(i);
                System.out.println(lItErA + ": " + gEnErUjLiNiEhIsToGrAmU(iLoSc, oBliCzSkAlE(lIsTaWyStEpOwAnIa)));
            }
        }
    }
    private static void gEnErUjHiStOgRaM(ArrayList<Character> lIsTaZnAkOw, ArrayList<Integer> lIsTaWyStEpOwAnIa, String nAzWaPlIkU) { // Generowanie histogramu do pliku txt
        try (PrintWriter wRiTeR = new PrintWriter(nAzWaPlIkU)) {
            wRiTeR.println("Histogram częstości występowania liter:");

            for (int i = 0; i < lIsTaZnAkOw.size(); i++) {
                if(lIsTaWyStEpOwAnIa.get(i) != 0) {
                    char lItErA = lIsTaZnAkOw.get(i);
                    int iLoSc = lIsTaWyStEpOwAnIa.get(i);
                    wRiTeR.println(lItErA + ": " + gEnErUjLiNiEhIsToGrAmU(iLoSc, oBliCzSkAlE(lIsTaWyStEpOwAnIa)));
                }
            }
            System.out.println("Histogram został zapisany do pliku " + nAzWaPlIkU);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
    private static String gEnErUjLiNiEhIsToGrAmU(int iLoSc, int sKaLa) {
        StringBuilder hIsToGrAm = new StringBuilder();
        for (int i = 0; i < (iLoSc / sKaLa); i++) {
            hIsToGrAm.append("█");
        }
        return hIsToGrAm.toString();
    }
    private static ArrayList<Character> zMiAnAdOcHaR(String sPrAwDzTeZnAkI){
        ArrayList<Character> lIsTaznAkOw = new ArrayList<>();

        for (char x: sPrAwDzTeZnAkI.toLowerCase().toCharArray()) {
            if(x != ',' && x != ' '){
                lIsTaznAkOw.add(x);
            }
        }
        return lIsTaznAkOw;
    }
    private static int oBliCzSkAlE(ArrayList<Integer> lIsTaWyStEpOwAnIa){ // Oblicza skale w jakiej się będzie wyświetlał histogram w przypadku otrzymania dużej liczby danych
        int sKaLa = 1;
        if (Max(lIsTaWyStEpOwAnIa) > 50) {
            sKaLa = Max(lIsTaWyStEpOwAnIa) / 50;
        }
        return sKaLa;
    }
    public static int Max(ArrayList<Integer> lIsTaWyStEpOwAnIa) { //Zwraca największy element w Array
        int nAjWiEkSzY = lIsTaWyStEpOwAnIa.get(0);

        for (int i = 0; i < lIsTaWyStEpOwAnIa.size(); i++) {
            if (lIsTaWyStEpOwAnIa.get(i) > nAjWiEkSzY) {
                nAjWiEkSzY = lIsTaWyStEpOwAnIa.get(i);
            }
        }
        return nAjWiEkSzY; // Zwracamy znaleziony największy element.
    }
}