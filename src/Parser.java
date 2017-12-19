import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

abstract class Parser {
    String coParsujemy;

    Parser(String co) {
        coParsujemy = co;
    }

    public Fragment WczytujeIPoprawia() throws IOException {
        //String string=new String();
        //if (coParsujemy.equals("konstytucja")) string="/Users/ImI/IdeaProjects/konstytucja/src/konstytucja.txt";
        //else string="/Users/ImI/IdeaProjects/konstytucja/src/uokik.txt";

        Path sciezka = Paths.get(coParsujemy);
        BufferedReader tekst = Files.newBufferedReader(sciezka);
        List<String> listaStringów = new LinkedList();    //wczytuję tekst do listyStringów
        for (String line; (line = tekst.readLine()) != null; listaStringów.add(line)) ;

        Tekst s = new Tekst(listaStringów);
        listaStringów = s.usuwaZnaczki();
        listaStringów = s.załatwiaProblemPrzeniesieniaLinii();
        listaStringów = s.dzieliNaCzytelneWersy();
        listaStringów = zrobPreprocessing(s, listaStringów);

        List<Fragment> listaFragmentów = new LinkedList<>();
        Fragment fragment = new Fragment(TypFragmentu.Root, "root", 1);
        fragment = s.Strukturyzuje();
        return fragment;
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
