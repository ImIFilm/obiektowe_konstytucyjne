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
        System.out.println("|" + fra.numer+"|--- "+fra.treść+" ");
        for (int i=0; i<fra.lista.size(); i++)
        {
            if (fra.lista.get(i)!=null) WypiszWgłąb(fra.lista.get(i));
        }


    }
}
