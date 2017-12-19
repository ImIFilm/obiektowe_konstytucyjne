import java.util.LinkedList;
import java.util.List;

public class Fragment {
    TypFragmentu typ;
    String treść;
    int numer=0;
    List<Fragment> lista = new LinkedList<>();
    char litera='-';

    Fragment() {
        typ = TypFragmentu.Root;
    }

    Fragment(TypFragmentu t, String s, int o) {
        typ = t;
        treść = s;
        numer = o;
    }

    Fragment(TypFragmentu t, String s, List<Fragment> l, int o) {
        typ = t;
        treść = s;
        lista = l;
        numer = o;
    }

    Fragment(TypFragmentu t, String s, char z) {
        typ = t;
        treść = s;
        litera = z;
    }

    Fragment(TypFragmentu t, String s, List<Fragment> l, int o, char z) {
        typ = t;
        treść = s;
        lista = l;
        numer = o;
        litera=z;
    }


    @Override
    public String toString() {
        return treść;
    }

    public void WypiszWgłąb(Fragment fra) {
        System.out.println("| numer: " + fra.numer + fra.litera + " -> " + fra.typ + "|--- " + fra.treść + " "); //zwraca stringi!!!!!!!!!!!!!
        for (int i = 0; i < fra.lista.size(); i++) {
            if (fra.lista.get(i) != null) WypiszWgłąb(fra.lista.get(i));
        }
    }

    public void WypiszArtykuły(Fragment fra) {
        if (fra.typ == TypFragmentu.Artykuł) {
            System.out.println("|" + fra.numer + fra.litera + "|--- " + fra.treść + " ");
        }
        for (int i = 0; i < fra.lista.size(); i++) {
            if (fra.lista.get(i) != null) WypiszArtykuły(fra.lista.get(i));
        }
    }

    public void WypiszSpis(Fragment fra) {
        if (
                (fra.typ == TypFragmentu.Rozdział || fra.typ == TypFragmentu.Root) &&
                        (fra.treść != null) &&
                        (fra.treść.length() > 3) &&
                        !(fra.treść.substring(0, 4).matches("\\p{Lu}"))
                ) {
            if (fra.typ == TypFragmentu.Root) System.out.println("|" + fra.numer + "|---   ↳" + fra.treść + " ");
            else System.out.println("|" + fra.numer + "|--- " + fra.treść + " ");
        }
        for (int i = 0; i < fra.lista.size(); i++) {
            if (fra.lista.get(i) != null) WypiszSpis(fra.lista.get(i));
        }
    }


    public void WypiszArtykułKonstytucji(int a, Fragment fra) {
        for (int i = 0; i < fra.lista.size(); i++) {
            for (int j = 0; j < fra.lista.get(i).lista.size(); j++) {
                if (fra.lista.get(i).lista.get(j).typ == TypFragmentu.Artykuł && fra.lista.get(i).lista.get(j).numer == a) {
                    WypiszWgłąb(fra.lista.get(i).lista.get(j));
                }
            }
        }
    }

    public void WypiszArtykułKonstytucjiZZakresu(int a, int b, Fragment fra) {
        for (int i = 0; i < fra.lista.size(); i++) {
            for (int j = 0; j < fra.lista.get(i).lista.size(); j++) {
                if (fra.lista.get(i).lista.get(j).typ == TypFragmentu.Artykuł && fra.lista.get(i).lista.get(j).numer > a - 1 &&
                        fra.lista.get(i).lista.get(j).numer < b + 1) {
                    WypiszWgłąb(fra.lista.get(i).lista.get(j));
                }
            }
        }
    }

    public void WypiszRozdziałKonstytucji(int a, Fragment fra) {
        for (int j = 0; j < fra.lista.size(); j++) {
            if (fra.lista.get(j).typ == TypFragmentu.Rozdział && fra.lista.get(j).numer == a) {
                WypiszWgłąb(fra.lista.get(j));
            }
        }
    }

    public void WypiszRozdziałKonstytucjiZZakresu(int a, int b, Fragment fra) {
        for (int j = 0; j < fra.lista.size(); j++) {
            if (fra.lista.get(j).typ == TypFragmentu.Rozdział && fra.lista.get(j).numer >= a && fra.lista.get(j).numer <= b) {
                WypiszWgłąb(fra.lista.get(j));
            }
        }
    }
}
