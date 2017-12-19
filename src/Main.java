import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

//plik ktory ma nie to co trzeba
//odwrotny zakres
//artykul ktorego nie ma, punkty ktorych nie ma
public class Main
{
    public static void main(String[] args) throws IOException {

        ParserKonstytucji konstytucja = new ParserKonstytucji("/Users/ImI/IdeaProjects/konstytucja/src/konstytucja.txt");
        List<String> e = new LinkedList();
        Fragment fragment=konstytucja.WczytujeIPoprawia();
        //fragment.WypiszArtykuły(fragment);
        //fragment.WypiszSpis(fragment);
        //fragment.WypiszWgłąb(fragment);
        //fragment.WypiszArtykułKonstytucjiZZakresu(191, 200, fragment);
        //fragment.WypiszRozdziałKonstytucjiZZakresu(3, 4, fragment);
        //fragment.WypiszArtykułKonstytucji(3, fragment);
        //fragment.WypiszRozdziałKonstytucji(3, fragment);

        ParserUOKIK uokik = new ParserUOKIK("/Users/ImI/IdeaProjects/konstytucja/src/uokik.txt");
        List<String> u = new LinkedList();
        Fragment fragment2=uokik.WczytujeIPoprawia();
        //fragment2.WypiszWgłąb(fragment2);
        //fragment.WypiszSpis(fragment2);

        u=uokik.WczytujeIListuje();
        //*WYPISUJE*/ for (int i=0; i<u.size(); i++) System.out.println(i+": "+u.get(i));


        e=konstytucja.WczytujeIListuje();
        //*WYPISUJE*/ for (int i=0; i<e.size(); i++) System.out.println(i+": "+e.get(i));

            }
}
