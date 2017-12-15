import java.util.LinkedList;
import java.util.List;

public class Fragment {
    TypFragmentu typ;
    String treść;
    int numer;
    List<Fragment> lista = new LinkedList<>();


    Fragment (TypFragmentu t, String s, List<Fragment> l, int o)
    {
        typ=t;
        treść=s;
        lista=l;
        numer=o;
    }

    Fragment (TypFragmentu t, String s, int o)
    {
        typ=t;
        treść=s;
        numer=o;
    }

    Fragment()
    {
        typ=TypFragmentu.Root;
    }

    @Override
    public String toString() {
        return treść;
    }

    public void WypiszWgłąb(Fragment fra)
    {
        System.out.println("| numer: " + fra.numer+" -> "+fra.typ+"|--- "+fra.treść+" ");
        for (int i=0; i<fra.lista.size(); i++)
        {
            if (fra.lista.get(i)!=null) WypiszWgłąb(fra.lista.get(i));
        }
    }

    public void WypiszArtykuły(Fragment fra)
    {
        if (fra.typ==TypFragmentu.Artykuł) {
            System.out.println("|" + fra.numer + "|--- " + fra.treść + " ");
        }
        for (int i=0; i<fra.lista.size(); i++)
        {
            if (fra.lista.get(i)!=null) WypiszArtykuły(fra.lista.get(i));
        }
    }

    public void WypiszSpis(Fragment fra)
    {
        if (
                (fra.typ == TypFragmentu.Rozdział || fra.typ == TypFragmentu.Root) &&
                        (fra.treść!=null) &&
                        (fra.treść.length()>3) &&
                       !(fra.treść.substring(0,4).matches("\\p{Lu}"))


           )
        {
            if (fra.typ == TypFragmentu.Root)System.out.println("|" + fra.numer + "|---        " + fra.treść + " ");
            else System.out.println("|" + fra.numer + "|--- " + fra.treść + " ");
        }
        for (int i=0; i<fra.lista.size(); i++)
        {
            if (fra.lista.get(i)!=null) WypiszSpis(fra.lista.get(i));
        }
    }
}
