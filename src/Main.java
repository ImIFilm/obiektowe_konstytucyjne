import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

//plik ktory ma nie to co trzeba
//odwrotny zakres
//artykul ktorego nie ma, punkty ktorych nie ma
public class Main
{
    public static void main(String[] args) throws IOException {


        //dzieci wypisuje tylko do jednej instancji
        //for(MyTreeNode node: a1.getChildren())
        //{
        //    System.out.println(node.getData());
       // }



        ParserKonstytucji konstytucja = new ParserKonstytucji("/Users/ImI/IdeaProjects/konstytucja/src/konstytucja.txt");
        List<String> e = new LinkedList();
        Fragment fragment=konstytucja.WczytujeIPoprawia();
        //fragment.WypiszArtykuły(fragment);
        fragment.WypiszSpis(fragment);
        //fragment.WypiszWgłąb(fragment);


        //for (int i=0; i<fragment.lista.size(); i++) System.out.println(i+": "+fragment.lista.get(i));
        //Fragment fragment2=fragment.lista.get(7);


        e=konstytucja.WczytujeIListuje();
        /*WYPISUJE*/ for (int i=0; i<e.size(); i++) System.out.println(i+": "+e.get(i));








       // Parser uokik = new ParserUOKIK("uokik");
       // List<String> a = new LinkedList();
        //a=uokik.WczytujeIListuje();
        //*WYPISUJE*/ for (int i=0; i<a.size(); i++) System.out.println(i+": "+a.get(i));




    }
}
