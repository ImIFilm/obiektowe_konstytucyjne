import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Parser {
    String coParsujemy;
    Parser (String co)
    {
        coParsujemy=co;
    }

    public Fragment WczytujeIPoprawia() throws IOException {
        String string=new String();

        if (coParsujemy.equals("konstytucja")) string="/Users/ImI/IdeaProjects/konstytucja/src/konstytucja.txt";
        else string="/Users/ImI/IdeaProjects/konstytucja/src/uokik.txt";

        Path sciezka = Paths.get(string);
        BufferedReader tekst= Files.newBufferedReader(sciezka);
        List<String> listaStringów=new LinkedList();    //wczytuję tekst do listyStringów
        for (String line; (line=tekst.readLine()) !=null; listaStringów.add(line));

        Plik s=new Plik(listaStringów);
        listaStringów=s.usuwaZnaczki();
        listaStringów=s.załatwiaProblemPrzeniesieniaLinii();
        listaStringów=s.dzieliNaCzytelneWersy();
        if(coParsujemy.equals("uokik")) listaStringów=s.uporczyweArtykuły(); //jesli uokik, to trzeba poradzic sobie z nowym problemem

        List<Fragment> listaFragmentów=new LinkedList<>();
        Fragment fragment=new Fragment(TypFragmentu.Root, "root");
        fragment=s.Strukturyzuje();
        return fragment;
        }


}
