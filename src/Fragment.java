import java.util.LinkedList;
import java.util.List;

public class Fragment {
    TypFragmentu typ;
    String treść;
    List<Fragment> lista = new LinkedList<>();

    Fragment (TypFragmentu t, String s, List<Fragment> l)
    {
        typ=t;
        treść=s;
        lista=l;
    }

    Fragment (TypFragmentu t, String s)
    {
        typ=t;
        treść=s;
    }

    Fragment()
    {
        typ=TypFragmentu.Root;
    }

    @Override
    public String toString() {
        return treść;
    }
}
