abstract class Fragment {
    int numer;
    String treść;

    public String stringFragmentu()
    {
        String string = new String();
        string=numer + " " + treść;
        return string;
    }

}
