import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("----PROGRAM----\n\nDomyślne lokalizacje naszych plików");
        System.out.println("KONSTYTUCJA:  /Users/ImI/IdeaProjects/konstytucja/src/konstytucja.txt\nUOKIK:        /Users/ImI/IdeaProjects/konstytucja/src/uokik.txt");
        System.out.println("Wpisz ścieżkę do pliku:");
        //wczytujemy siezke
        String sciezka;
        Scanner odczyt = new Scanner(System.in);
        sciezka = odczyt.nextLine();
        //}
        //rezpoznajemy co to za sciezka
        ParserConstitution test = new ParserConstitution(sciezka);
        ParserConstitution text1;
        ParserUOKIK tekst2;
        Extract extract;
        if (test.IsConstitution()) {
            text1 = new ParserConstitution(sciezka);
            extract = text1.CreateAndImprove();
        } else {
            tekst2 = new ParserUOKIK(sciezka);
            extract = tekst2.CreateAndImprove();
        }
        //prosimy o podanie trybu
        boolean flag1 = true;
        String tryb = null;
        Scanner odczyt2;
        while (flag1) {
            System.out.println("Podaj tryb działania: SPIS lub TRESC");
            odczyt2 = new Scanner(System.in);
            tryb = odczyt2.nextLine();
            if (tryb.equals("SPIS") || tryb.equals("TRESC")) {
                flag1 = false;
                System.out.println("WCZYTANO:" + tryb);
            } else System.out.println("źle coś wpisałeś, spróbuj jeszcze raz");
        }
        //SPIS
        if (tryb.equals("SPIS")) {
            extract.ShowTableOfContent(extract);
        }

        //FRAGMENT
        else {
            boolean flag2 = true;
            String element = null;
            while (flag2) {
                System.out.println("Co chcesz wyświetlić? Wybierz:\nROZDZIAŁ / ARTYKUŁ / USTĘP / PUNKT / LITERA");
                Scanner odczyt3 = new Scanner(System.in);
                element = odczyt3.nextLine();
                if (element.equals("ARTYKUŁ") || element.equals("USTĘP") || element.equals("PUNKT")
                        || element.equals("LITERA") || element.equals("ROZDZIAŁ")) {
                    flag2 = false;
                    System.out.println("WCZYTANO:" + element);
                } else System.out.println("źle coś wpisałeś, spróbuj jeszcze raz");
            }
            flag2 = true;
            if (element.equals("ARTYKUŁ")) {

                boolean zleIle = true;
                String what = null;
                while (zleIle) {
                    System.out.println("Jeden artykuł czy zakres? Wpisz JEDEN lub ZAKRES");
                    Scanner odczyt4 = new Scanner(System.in);
                    what = odczyt4.nextLine();
                    if (what.equals("JEDEN") || what.equals("ZAKRES")) {
                        zleIle = false;
                        System.out.println("WCZYTANO:" + what);
                    } else System.out.println("źle coś wpisałeś, spróbuj jeszcze raz");
                }
                if (what.equals("ZAKRES"))
                {
                    boolean flag3=true;
                    int start =0;
                    int end =0;
                    while(flag3){
                        System.out.println("Wpisz start i end (rozdziel spacją)");
                        Scanner read5 = new Scanner(System.in);
                        start = read5.nextInt();
                        end = read5.nextInt();
                        if (start <0 || end <0) {
                            System.out.println("Liczba ujemna? Tak jeszcze nie numerują ustaw! Spróbuj jescze raz!");
                        }
                        else if (start > end)
                        {
                            System.out.println("Początek większy od końca, ciekawe... Spróbuj jeszcze raz!");
                        }
                        else
                        {
                            System.out.println("WCZYTANO:" + start + "-" + end);
                            flag3=false;
                        }
                    }
                    extract.ShowArticlesFromTo(start, end, extract);

                }
                else
                {
                    boolean flag4 =true;
                    int poczatek=0;
                    while(flag4){
                        System.out.println("Wpisz numer");
                        Scanner read6 = new Scanner(System.in);
                        poczatek= read6.nextInt();
                        if (poczatek<0) {
                            System.out.println("Liczba ujemna? Tak jeszcze nie numerują ustaw! Spróbuj jescze raz!");
                        }
                        else
                        {
                            System.out.println("WCZYTANO:  " + poczatek);
                            flag4 =false;
                        }
                    }
                    //System.out.println(poczatek);
                    if (test.IsConstitution()) extract.ShowSpecificArticle(poczatek, extract);
                        //extract.WypiszCoWeSciezce(poczatek, extract);
                    else
                    {
                        char letter1='-';
                        System.out.println("Chcesz odczytać UOKIK, więc może chcesz też sprecyzować literkę przy numerze artykułu?");
                        System.out.println("Jeśli tak, wpisz ją poniżej, jeśli nie - wpisz 0");
                        Scanner odczyt6 = new Scanner(System.in);
                        letter1=odczyt6.nextLine().charAt(0);
                        if (letter1=='0') extract.ShowSpecificArticle(poczatek, extract);
                        else {
                            System.out.println("WCZYTANO:" + letter1);
                            extract.ShowSpecificArticle(poczatek, extract, letter1);
                        }


                        }
                    }
                }
            else if (element.equals("USTĘP")) {

                boolean flag6 =true;
                int art=0;
                int ust=0;
                while(flag6){
                    System.out.println("Wpisz number artykułu i po spacji number ustępu");
                    Scanner read9 = new Scanner(System.in);
                    art=read9.nextInt();
                    ust=read9.nextInt();
                    if (art<0 || ust<0) {
                        System.out.println("Liczba ujemna? Tak jeszcze nie numerują ustaw! Spróbuj jescze raz!");
                    }
                    else
                    {
                        System.out.println("WCZYTANO: Art:" + art + " Ust." + ust);
                        flag6 =false;
                    }
                }
                extract.ShowSpecificSection(art, ust, extract);
            }

            else if (element.equals("PUNKT")) {

                boolean zleDane=true;
                int art=0;
                int ust=0;
                int pkt=0;
                while(zleDane){
                    System.out.println("Wpisz number artykułu I po spacji numer ustępu i punktu");
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
                extract.ShowSpecificPoint(art, ust, pkt, extract);
            }
            else if (element.equals("LITERA")) {

                boolean flag8 =true;
                int art=0;
                int ust=0;
                int pkt=0;
                char letter ='-';
                while(flag8){
                    System.out.println("Wpisz number artykułu i po spacji numer ustępu, punktu i literę");
                    Scanner read10 = new Scanner(System.in);
                    art= read10.nextInt();
                    ust= read10.nextInt();
                    pkt= read10.nextInt();
                    letter = read10.nextLine().charAt(1);
                    if (art<0 || ust<0 || pkt<0) {
                        System.out.println("Liczba ujemna? Tak jeszcze nie numerują ustaw! Spróbuj jescze raz!");
                    }
                    else
                    {
                        System.out.println("WCZYTANO: Art:" + art + " Ust." + ust + " Pkt." + pkt + " Lit:" + letter);
                        flag8 =false;
                    }
                }
                extract.ShowSpecificSign(art, ust, pkt, letter, extract);
            }
            else if (element.equals("ROZDZIAŁ"))
            {
                boolean flag12=true;
                int start2=0;
                while(flag12){
                    System.out.println("Wpisz numer rozdziału");
                    Scanner odczyt8 = new Scanner(System.in);
                    start2=odczyt8.nextInt();
                    if (start2<0) {
                        System.out.println("Liczba ujemna? Tak jeszcze nie numerują ustaw! Spróbuj jescze raz!");
                    }
                    else
                    {
                        System.out.println("WCZYTANO:  " + start2);
                        flag12=false;
                    }
                }
                if (test.IsConstitution())//System.out.println(poczatek);
                extract.ShowSpecificChapter(start2, extract);
                else{
                    boolean flag13=true;
                    int start23=0;
                    while(flag13){
                        System.out.println("To UOKIK, wpisz numer działu, z którego ma być rozdział");
                        Scanner odczyt11 = new Scanner(System.in);
                        start23=odczyt11.nextInt();
                        if (start23<0) {
                            System.out.println("Liczba ujemna? Tak jeszcze nie numerują ustaw! Spróbuj jescze raz!");
                        }
                        else
                        {
                            System.out.println("WCZYTANO:  " + start23);
                            flag13=false;
                        }
                    }
                        extract.ShowSpecificChapterFromUnit(start2, start23, extract);
                }
            }
            }
        ParserConstitution konstytucja = new ParserConstitution("/Users/ImI/IdeaProjects/konstytucja/src/konstytucja.txt");
        List<String> e = new LinkedList();
        Extract extract3 = konstytucja.CreateAndImprove();

        //extract3.ShowArticlesFromTo(extract3);
        //extract3.ShowTableOfContent(extract3);
        //extract3.DeepPreview(extract3);
        //extract3.ShowSpecificArticle(6, extract3);
        //extract3.ShowSpecificSection(191, 1, extract3, Section);
        //extract3.WypiszRozdziałKonstytucjiZZakresu(3, 4, extract3);
        //extract3.WypiszArtykułKonstytucji(6,extract3);
        //extract3.WypiszRozdziałKonstytucji(3, extract3);
        //extract3.WypiszCoWeSciezce(6, 1, extract3);
        //extract3.ShowArticlesFromTo(2, 10, extract3);
        //extract3.ShowSpecificSection(191, 1, extract3);
        //extract3.ShowSpecificPoint(191, 1, 4, extract3);
        //extract3.ShowSpecificSign(111,3,1,'a',extract3);

        ParserUOKIK uokik = new ParserUOKIK("/Users/ImI/IdeaProjects/konstytucja/src/uokik.txt");
        List<String> u = new LinkedList();
        Extract extract2 = uokik.CreateAndImprove();
        //extract2.WypiszCoWeSciezce(105, 1, 1, extract2);
        //extract2.DeepPreview(extract2);
        //extract2.ShowTableOfContent(extract2);
        //extract2.WypiszRozdziałKonstytucji(7, extract2);
        //extract2.ShowArticlesFromTo(105, 105, extract2);
        //extract2.WypiszCoWeSciezce(100,2, extract);
        //extract2.ShowSpecificArticle(6, extract2);
        //extract2.ShowSpecificSection(6, 1, extract2, ExtractType.Article);
        //extract2.ShowArticlesFromTo(2, 10, extract2);
        //extract2.ShowSpecificSign(111,3,1,'a',extract2);

        u = uokik.ReadAndList();
        //*WYPISUJE*/ for (int i=0; i<u.size(); i++) System.out.println(i+": "+u.get(i));


        e = konstytucja.ReadAndList();
        //*WYPISUJE*/ for (int i=0; i<e.size(); i++) System.out.println(i+": "+e.get(i));

    }
}
