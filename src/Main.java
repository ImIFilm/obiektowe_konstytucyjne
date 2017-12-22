import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

//plik ktory ma nie to co trzeba
//odwrotny zakres
//artykul ktorego nie ma, punkty ktorych nie ma
public class Main {
    public static void main(String[] args) throws IOException {

/*
        System.out.println("----PROGRAM----\n\nDomyślne lokalizacje naszych plików");
        System.out.println("KONSTYTUCJA:  /Users/ImI/IdeaProjects/konstytucja/src/konstytucja.txt\nUOKIK:        /Users/ImI/IdeaProjects/konstytucja/src/uokik.txt");
        System.out.println("Wpisz ścieżkę do pliku:");
        //wczytujemy siezke
        String sciezka;
        Scanner odczyt = new Scanner(System.in);
        sciezka = odczyt.nextLine();
        //}
        //rezpoznajemy co to za sciezka
        ParserKonstytucji test = new ParserKonstytucji(sciezka);
        ParserKonstytucji tekst1;
        ParserUOKIK tekst2;
        Extract extract;
        if (test.CzyKonstytucja()) {
            tekst1 = new ParserKonstytucji(sciezka);
            extract = tekst1.WczytujeIPoprawia();
        } else {
            tekst2 = new ParserUOKIK(sciezka);
            extract = tekst2.WczytujeIPoprawia();
        }
        //prosimy o podanie trybu
        boolean zlyTryb = true;
        String tryb = null;
        Scanner odczyt2;
        while (zlyTryb) {
            System.out.println("Podaj tryb działania: SPIS lub TRESC");
            odczyt2 = new Scanner(System.in);
            tryb = odczyt2.nextLine();
            if (tryb.equals("SPIS") || tryb.equals("TRESC")) {
                zlyTryb = false;
                System.out.println("WCZYTANO:" + tryb);
            } else System.out.println("źle coś wpisałeś, spróbuj jeszcze raz");
        }
        //SPIS
        if (tryb.equals("SPIS")) {
            extract.ShowTableOfContent(extract);
        }

        //FRAGMENT
        else {
            boolean zlyElement = true;
            String element = null;
            while (zlyElement) {
                System.out.println("Co chcesz wyświetlić? Wybierz:\nARTYKUŁ / USTĘP / PUNKT / LITERA");
                Scanner odczyt3 = new Scanner(System.in);
                element = odczyt3.nextLine();
                if (element.equals("ARTYKUŁ") || element.equals("USTĘP") || element.equals("PUNKT") || element.equals("LITERA")) {
                    zlyElement = false;
                    System.out.println("WCZYTANO:" + element);
                } else System.out.println("źle coś wpisałeś, spróbuj jeszcze raz");
            }
            zlyElement = true;
            if (element.equals("ARTYKUŁ")) {

                boolean zleIle = true;
                String ile = null;
                while (zleIle) {
                    System.out.println("Jeden artykuł czy zakres? Wpisz JEDEN lub ZAKRES");
                    Scanner odczyt4 = new Scanner(System.in);
                    ile = odczyt4.nextLine();
                    if (ile.equals("JEDEN") || ile.equals("ZAKRES")) {
                        zleIle = false;
                        System.out.println("WCZYTANO:" + ile);
                    } else System.out.println("źle coś wpisałeś, spróbuj jeszcze raz");
                }
                if (ile.equals("ZAKRES"))
                {
                    boolean zlyZakres=true;
                    int poczatek=0;
                    int koniec=0;
                    while(zlyZakres){
                        System.out.println("Wpisz poczatek i koniec (rozdziel spacją)");
                        Scanner odczyt5 = new Scanner(System.in);
                        poczatek=odczyt5.nextInt();
                        koniec=odczyt5.nextInt();
                        if (poczatek<0 || koniec<0) {
                            System.out.println("Liczba ujemna? Tak jeszcze nie numerują ustaw! Spróbuj jescze raz!");
                        }
                        else if (poczatek>koniec)
                        {
                            System.out.println("Początek większy od końca, ciekawe... Spróbuj jeszcze raz!");
                        }
                        else
                        {
                            System.out.println("WCZYTANO:" + poczatek + "-" + koniec);
                            zlyZakres=false;
                        }
                    }
                    extract.ZAKRESWypiszArtykuły(poczatek, koniec, extract);

                }
                else
                {
                    boolean zlyZakres=true;
                    int poczatek=0;
                    while(zlyZakres){
                        System.out.println("Wpisz number");
                        Scanner odczyt5 = new Scanner(System.in);
                        poczatek=odczyt5.nextInt();
                        if (poczatek<0) {
                            System.out.println("Liczba ujemna? Tak jeszcze nie numerują ustaw! Spróbuj jescze raz!");
                        }
                        else
                        {
                            System.out.println("WCZYTANO:  " + poczatek);
                            zlyZakres=false;
                        }
                    }
                    //System.out.println(poczatek);
                    if (test.CzyKonstytucja()) extract.ShowSpecificArticle(poczatek, extract);
                        //extract.WypiszCoWeSciezce(poczatek, extract);
                    else
                    {
                        char literka='-';
                        System.out.println("Chcesz odczytać UOKIK, więc może chcesz też sprecyzować literkę przy numerze artykułu?");
                        System.out.println("Jeśli tak, wpisz ją poniżej, jeśli nie - wpisz 0");
                        Scanner odczyt6 = new Scanner(System.in);
                        literka=odczyt6.nextLine().charAt(0);
                        if (literka=='0') extract.ShowSpecificArticle(poczatek, extract);
                        else {
                            System.out.println("WCZYTANO:" + literka);
                            extract.ShowSpecificArticle(poczatek, extract, literka);
                        }


                        }
                    }
                }
            else if (element.equals("USTĘP")) {

                boolean zleDane=true;
                int art=0;
                int ust=0;
                while(zleDane){
                    System.out.println("Wpisz number artykułu i po spacji number ustępu");
                    Scanner odczyt5 = new Scanner(System.in);
                    art=odczyt5.nextInt();
                    ust=odczyt5.nextInt();
                    if (art<0 || ust<0) {
                        System.out.println("Liczba ujemna? Tak jeszcze nie numerują ustaw! Spróbuj jescze raz!");
                    }
                    else
                    {
                        System.out.println("WCZYTANO: Art:" + art + " Ust." + ust);
                        zleDane=false;
                    }
                }
                //extract.ShowSpecificSection(6, 1, extract);
                //extract.WypiszCoWeSciezce(art, ust, extract);
            }

            else if (element.equals("PUNKT")) {

                boolean zleDane=true;
                int art=0;
                int ust=0;
                int pkt=0;
                while(zleDane){
                    System.out.println("Wpisz number artykułu i po spacji number ustępu");
                    Scanner odczyt5 = new Scanner(System.in);
                    art=odczyt5.nextInt();
                    ust=odczyt5.nextInt();
                    pkt=odczyt5.nextInt();
                    if (art<0 || ust<0 || pkt<0) {
                        System.out.println("Liczba ujemna? Tak jeszcze nie numerują ustaw! Spróbuj jescze raz!");
                    }
                    else
                    {
                        System.out.println("WCZYTANO: Art:" + art + " Ust." + ust + " Pkt." + pkt);
                        zleDane=false;
                    }
                }
                extract.WypiszCoWeSciezce(art, ust, pkt, extract);
            }
            else if (element.equals("LITERA")) {

                boolean zleDane=true;
                int art=0;
                int ust=0;
                int pkt=0;
                char litera='-';
                while(zleDane){
                    System.out.println("Wpisz number artykułu i po spacji number ustępu");
                    Scanner odczyt5 = new Scanner(System.in);
                    art=odczyt5.nextInt();
                    ust=odczyt5.nextInt();
                    pkt=odczyt5.nextInt();
                    litera=odczyt5.nextLine().charAt(1);
                    if (art<0 || ust<0 || pkt<0) {
                        System.out.println("Liczba ujemna? Tak jeszcze nie numerują ustaw! Spróbuj jescze raz!");
                    }
                    else
                    {
                        System.out.println("WCZYTANO: Art:" + art + " Ust." + ust + " Pkt." + pkt + " Lit:" + litera);
                        zleDane=false;
                    }
                }
                extract.WypiszCoWeSciezce(art, ust, pkt, litera, extract);
            }
            }
*/
        ParserKonstytucji konstytucja = new ParserKonstytucji("/Users/ImI/IdeaProjects/konstytucja/src/konstytucja.txt");
        List<String> e = new LinkedList();
        Extract extract3 = konstytucja.WczytujeIPoprawia();

        //extract3.ZAKRESWypiszArtykuły(extract3);
        //extract3.ShowTableOfContent(extract3);
        //extract3.DeepPreview(extract3);
        //extract3.ShowSpecificArticle(6, extract3);
        //extract3.ShowSpecificSection(191, 1, extract3, Section);
        //extract3.WypiszRozdziałKonstytucjiZZakresu(3, 4, extract3);
        //extract3.WypiszArtykułKonstytucji(6,extract3);
        //extract3.WypiszRozdziałKonstytucji(3, extract3);
        //extract3.WypiszCoWeSciezce(6, 1, extract3);
        extract3.ZAKRESWypiszArtykuły(2, 10, extract3);

        ParserUOKIK uokik = new ParserUOKIK("/Users/ImI/IdeaProjects/konstytucja/src/uokik.txt");
        List<String> u = new LinkedList();
        Extract extract2 = uokik.WczytujeIPoprawia();
        //extract2.WypiszCoWeSciezce(105, 1, 1, extract2);
        //extract2.DeepPreview(extract2);
        //extract2.ShowTableOfContent(extract2);
        //extract2.ZAKRESWypiszArtykuły(105, 105, extract2);
        //extract2.WypiszCoWeSciezce(100,2, extract);
        //extract2.ShowSpecificArticle(6, extract2);
        //extract2.ShowSpecificSection(6, 1, extract2, ExtractType.Article);
        //extract2.ZAKRESWypiszArtykuły(2, 10, extract2);

        u = uokik.WczytujeIListuje();
        //*WYPISUJE*/ for (int i=0; i<u.size(); i++) System.out.println(i+": "+u.get(i));


        e = konstytucja.WczytujeIListuje();
        //*WYPISUJE*/ for (int i=0; i<e.size(); i++) System.out.println(i+": "+e.get(i));

    }
}
