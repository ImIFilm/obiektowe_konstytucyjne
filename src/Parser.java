import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

abstract class Parser {
    String coParsujemy;

    Parser(String co) {
        coParsujemy = co;
    }

    public boolean CzyKonstytucja() throws IOException {
        try {
            Path sciezka = Paths.get(coParsujemy);
            BufferedReader tekst = Files.newBufferedReader(sciezka);
        }
        catch (NoSuchFileException e)
        {
            System.out.println("nie ma takiego pliku, uruchom program jeszcze raz i wpisz poprawną ścieżkę");
            System.exit(10);
        }
        catch (IOException e)
        {
            System.out.println("błąd wejścia/wyjścia");
            System.exit(11);
        }

        Path sciezka = Paths.get(coParsujemy);
        BufferedReader tekst = Files.newBufferedReader(sciezka);
        List<String> listaStringów = new LinkedList();    //wczytuję tekst do listyStringów
        tekst.readLine();
        tekst.readLine();
        if (tekst.readLine().matches("KONSTYTUCJA")) return true;
        return false;
    }


    public Extract WczytujeIPoprawia() throws IOException {
        //String string=new String();
        //if (coParsujemy.equals("konstytucja")) string="/Users/ImI/IdeaProjects/konstytucja/src/konstytucja.txt";
        //else string="/Users/ImI/IdeaProjects/konstytucja/src/uokik.txt";
        try {
            Path sciezka = Paths.get(coParsujemy);
            BufferedReader tekst = Files.newBufferedReader(sciezka);
        }
        catch (NoSuchFileException e)
        {
            System.out.println("nie ma takiego pliku, uruchom program jeszcze raz i wpisz poprawną ścieżkę");
            System.exit(10);
        }
        Path sciezka = Paths.get(coParsujemy);
        BufferedReader tekst = Files.newBufferedReader(sciezka);
        List<String> listaStringów = new LinkedList();    //wczytuję tekst do listyStringów
        for (String line; (line = tekst.readLine()) != null; listaStringów.add(line)) ;


        Tekst s = new Tekst(listaStringów);
        listaStringów = s.usuwaZnaczki();
        listaStringów = s.załatwiaProblemPrzeniesieniaLinii();
        listaStringów = s.dzieliNaCzytelneWersy();
        listaStringów = zrobPreprocessing(s, listaStringów);

        List<Extract> listaFragmentów = new LinkedList<>();
        Extract extract = new Extract(ExtractType.Root, "root", 1);
        extract = s.Strukturyzuje();
        return extract;


    }

    protected abstract List<String> zrobPreprocessing(Tekst s, List<String> listaStringów);

    public List<String> WczytujeIListuje() throws IOException {

        Path sciezka = Paths.get(coParsujemy);
        BufferedReader tekst = Files.newBufferedReader(sciezka);
        List<String> listaStringów = new LinkedList();    //wczytuję tekst do listyStringów
        for (String line; (line = tekst.readLine()) != null; listaStringów.add(line)) ;

        Tekst s = new Tekst(listaStringów);
        listaStringów = s.usuwaZnaczki();
        listaStringów = s.załatwiaProblemPrzeniesieniaLinii();
        listaStringów = s.dzieliNaCzytelneWersy();
        listaStringów = s.uporczyweArtykuły();

        return listaStringów;
    }

}
