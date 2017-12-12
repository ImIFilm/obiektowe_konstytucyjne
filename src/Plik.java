import java.util.List;

public class Plik {
    List<String> lista;
    Plik(List<String> lis)
    {
        lista=lis;
    }

    public List<String> getLista() {
        return lista;
    }

    public List<String> usuwaZnaczki(){
        lista.remove(0);
        for (int i=0; i<lista.size(); i++)
        {
            if (lista.get(i).equals("©Kancelaria Sejmu")) lista.remove(i);
            if (lista.get(i).equals("2009-11-16")) lista.remove(i);
            if (lista.get(i).equals("2017-06-28")) lista.remove(i);
            String string =lista.get(i);
            if (string.length()>4)
            {
                if (string.substring(0, 1).equals("©")) lista.remove(i);
                if (string.substring(0, 4).matches("\\d+")) lista.remove(i);
            }
        }

        for (int i=0; i<lista.size(); i++)
        {
            if (lista.get(i).equals("2017-06-28")) lista.remove(i);
        }

        for (int i=0; i<lista.size(); i++)
        {
            if (lista.get(i).equals("2009-11-16")) lista.remove(i);
        }
        return lista;
    }

    public List<String> załatwiaProblemPrzeniesieniaLinii()
    {
        for (int i=0; i<lista.size(); i++) //a może lepiej przez isEmpty>
        {
            int len=lista.get(i).length(); //dlugość
            String string = new String();
            string=lista.get(i);  //pobieram co jest w tym wierszu
            //przypadek gdy na końcu jest myślnik
            if (string.substring(len-1).equals("-"))
            {
                string = string.substring(0, string.length() - 1); //usunięcie myślnika z tego wiersza
                String string2=new String();
                string2 = lista.get(i+1);        //uzyskanie kolejnej linii
                string=string.concat(string2);             //połączenie linii
                lista.set(i, string);        //ustal na tym miejscu naszą nową linię
                lista.remove(i+1);   //skasuj ten kolejny wiersz
                i--;                                  //powtórz dla bezpieczeństwa
            }
        }
        return lista;
    }
    
    public List<String> dzieliNaCzytelneWersy()
    {
        for (int i=0; i<lista.size()-1; i++)
        {
            int len=lista.get(i).length(); //dlugość wersu
            String string = new String();
            string=lista.get(i+1);  //pobieram co jest następnym wierszu

            if (string.length()>4)
            {
                if (string.substring(0, 4).equals("Art.") ||                    //artykuł WORKS!
                        string.substring(0, 2).matches("\\d+[.)]") ||   //dowolny punkt w formacie liczba i kropka lub liczba i nawias WORKS!
                        string.substring(0, 3).matches("\\d+[.)]") ||  //j.w. WORKS!
                        string.substring(0, 4).equals("DZIA") ||            //rozdział WORKS!
                        string.substring(0, 4).equals("Rozd") ||          //dział WORKS!
                        string.substring(0, 1).matches("\\w[)]")) //wyrażenia jako a) i b)
                {

                }
                else
                {
                    String string2 = new String();
                    string2 = lista.get(i);
                    string2 = string2.concat(" ");       //uzyskanie kolejnej linii
                    string2 = string2.concat(string);   //połączenie linii
                    lista.set(i, string2);            //ustal na tym miejscu naszą nową linię
                    lista.remove(i + 1);      //skasuj ten kolejny wiersz
                    i--;                           //powtórz dla bezpieczeństwa
                }
            }
        }
        return lista;
    }

    public List<String> uporczyweArtykuły()
    {
        String string=new String();
        String string2=new String();
        for (int i=0; i<lista.size(); i++)
        {
            string=lista.get(i);
            if (string.length()>4 && string.substring(0, 4).equals("Art."))
            {
                string2=string.substring(4);
                for (int j = 0; j<9; j++)
                {
                    if (string2.charAt(j)=='.' && (string.charAt(j-1)=='0' || string.charAt(j-1)=='1' || string.charAt(j-1)=='2' || string.charAt(j-1)=='3' ||
                        string.charAt(j-1)=='4' || string.charAt(j-1)=='5' || string.charAt(j-1)=='6' || string.charAt(j-1)=='7' || string.charAt(j-1)=='8' ||
                        string.charAt(j-1)=='9'))
                    {
                        String string3=new String();
                        String string4=new String();
                        string3="Art."+string2.substring(0, j-1);
                        lista.set(i, string3);
                        string4=string2.substring(j-1);
                        lista.add(i+1, string4);
                        j++;
                    }
                }
            }
        }
        return lista;
    }


}
