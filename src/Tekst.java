import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tekst {
    List<String> lista;
    Tekst(List<String> lis)
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
            String string = new String();
            string=lista.get(i+1);  //pobieram co jest następnym wierszu
            String string3=new String();
            string3=lista.get(i);

            if (string.length()>3)
            {
                if (string.substring(0, 4).equals("Art.") ||                    //artykuł WORKS!
                        string.substring(0, 2).matches("\\d+[.)]") ||   //dowolny punkt w formacie liczba i kropka lub liczba i nawias WORKS!
                        string.substring(0, 3).matches("\\d+[.)]") ||  //j.w. WORKS!
                        string.substring(0, 4).equals("DZIA") ||            //rozdział WORKS!
                        string.substring(0, 4).equals("Rozd") ||          //dział WORKS!
                        string.substring(0, 2).matches("\\w\\)") || //wyrażenia jako a) i b)
                        (string.substring(0, 4).matches("[A-ZŚĆĄĘŻŹÓŁ]+") &&!(string3.length()<15 &&(string3.substring(0, 4).matches("Rozd")))) ||
                        string.substring(0, 4).equals("ŚROD") ||
                        //string.substring(0, 4).equals("ORGA") ||
                        string.substring(0, 4).equals("REFE") ||
                        ((string.length()<9)&& string.substring(0, 4).equals("SĄDY")) ||
                        string.substring(0, 4).equals("POSŁ")
                        )

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
                    i--;//powtórz dla bezpieczeństwa
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
            if (string.length()>3 && string.substring(0, 4).equals("Art."))
            {
                string2=string.substring(4);
                for (int j = 0; j<9; j++)
                {
                    if (string2.length()>7 && string.length()>7 &&
                            (string2.charAt(j)=='.' && (string.charAt(j-1)=='0' || string.charAt(j-1)=='1' || string.charAt(j-1)=='2' || string.charAt(j-1)=='3' ||
                        string.charAt(j-1)=='4' || string.charAt(j-1)=='5' || string.charAt(j-1)=='6' || string.charAt(j-1)=='7' || string.charAt(j-1)=='8' ||
                        string.charAt(j-1)=='9')))
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
        for (int i=0; i<lista.size(); i++)
        {
            Pattern patternUchylenia= Pattern.compile("\\(uchylony\\)");
            Matcher matcherU = patternUchylenia.matcher(lista.get(i));
            if (matcherU.find())
            {
                lista.remove(i);
                i--;
            }
        }
        return lista;
    }


    public Extract Strukturyzuje()
    {
        Extract root=new Extract();
        Extract AktualnyRozdział=null;//new Extract();
        Extract AktualnyArtykuł=null;
        Extract AktualnyPunkt=null;
        Extract AktualnyPodpunkt=null;
        Extract AktualnyDział=null;
        Extract Akt=root;


        for (int i=0; i<lista.size(); i++)
        {
            Pattern patternRozdziału = Pattern.compile("Rozdział");
            Matcher matcherRoz = patternRozdziału.matcher(lista.get(i));
            Pattern patternArtykułu = Pattern.compile("Art.");
            Matcher matcherArt = patternArtykułu.matcher(lista.get(i));
            Pattern patternPunktu = Pattern.compile("\\d+\\.");
            Matcher matcherPkt;
            if(lista.get(i).length()>3) matcherPkt = patternPunktu.matcher(lista.get(i).substring(0,3)); //zrobione, zeby sprawdzal
            else matcherPkt = patternPunktu.matcher(lista.get(i).substring(0,lista.get(i).length())); //tylko poczatek, nigdzie dalej
            Pattern patternPodpunktu = Pattern.compile("\\d+\\)");
            Matcher matcherPpkt = patternPodpunktu.matcher(lista.get(i));
            Pattern patternDziału = Pattern.compile("\\w*DZIAŁ\\w*");
            Matcher matcherDzia = patternDziału.matcher(lista.get(i));
            Pattern patternLitery = Pattern.compile("\\w\\)");
            Matcher matcherLitery = patternLitery.matcher(lista.get(i));

            if(matcherDzia.find()==true)
            {
                String line2 = lista.get(i);
                String pattern3 = "[XVI][XVI]*[XVI]*[XVI]*";
                Pattern r = Pattern.compile(pattern3);
                Matcher m = r.matcher(line2.substring(4));
                int foo=0;
                if (m.find( ))
                {
                    switch (m.group(0))
                    {
                        case ("I"): foo=1; break;
                        case ("II"): foo=2; break;
                        case ("III"): foo=3; break;
                        case ("IV"): foo=4; break;
                        case ("V"): foo=5; break;
                        case ("VI"): foo=6; break;
                        case ("VII"): foo=7; break;
                        case ("VIII"): foo=8; break;
                        case ("IX"): foo=9; break;
                        case ("X"): foo=10; break;
                        case ("XI"): foo=11; break;
                        case ("XII"): foo=12; break;
                        case ("XIII"): foo=13; break;
                        case ("XIV"): foo=14; break;
                        case ("XV"): foo=15; break;
                        case ("XVI"): foo=16; break;
                        default: {}
                    }
                }
                else {}
                Extract a = new Extract(ExtractType.Dział, lista.get(i), foo);
                root.list.add(a);
                AktualnyDział=a;
                Akt=a;
            }

            int foo=0;
            if(matcherRoz.find()==true) //jesli jest rozdziałem
            {
                String line = lista.get(i);
                String pattern = "[XVI][XVI]*[XVI]*[XVI]*";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(line);
                if (m.find( ))
                {
                    switch (m.group(0))
                    {
                        case ("I"): foo=1; break;
                        case ("II"): foo=2; break;
                        case ("III"): foo=3; break;
                        case ("IV"): foo=4; break;
                        case ("V"): foo=5; break;
                        case ("VI"): foo=6; break;
                        case ("VII"): foo=7; break;
                        case ("VIII"): foo=8; break;
                        case ("IX"): foo=9; break;
                        case ("X"): foo=10; break;
                        case ("XI"): foo=11; break;
                        case ("XII"): foo=12; break;
                        case ("XIII"): foo=13; break;
                        case ("XIV"): foo=14; break;
                        case ("XV"): foo=15; break;
                        case ("XVI"): foo=16; break;
                        default: {}
                    }

                }
                else
                {
                    String line2 = lista.get(i);
                    String pattern2 = "[1234567890]+";
                    Pattern r2 = Pattern.compile(pattern2);
                    Matcher m2 = r2.matcher(line2);
                    if (m2.find( )) foo=Integer.parseInt(m2.group());
                }
                Extract a = new Extract(ExtractType.Rozdział, lista.get(i), foo);
                if (AktualnyDział==null) root.list.add(a);
                else AktualnyDział.list.add(a);
                if(AktualnyRozdział!=null)AktualnyRozdział=a;
                Akt=a;
            }
            else if(matcherArt.find()==true) //jesli jest artykułem
            {
                String line = lista.get(i);
                String pattern = "[1234567890]+";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(line);
                foo = 0;
                if (m.find( )) foo=Integer.parseInt(m.group());
                char znak='-';
                Pattern q = Pattern.compile("\\d+\\w\\.");
                Matcher n = q.matcher(line);
                String s2;
                if (n.find())
                {
                    s2 = new String (n.group());
                    Pattern u = Pattern.compile("[a-z]");
                    Matcher w = u.matcher(s2);
                    if (w.find( )) znak=(w.group()).charAt(0);
                }


                Extract a = new Extract(ExtractType.Article, lista.get(i), foo, znak);
                if(AktualnyRozdział!=null)
                {
                    AktualnyRozdział.list.add(a);
                }
                else if (AktualnyDział!=null)
                {
                    AktualnyDział.list.add(a);
                }
                else root.list.add(a);
                AktualnyArtykuł=a;
                Akt=a;
            }
            else if(matcherPkt.find()==true) //jesli jest punktem
            {
                String line = lista.get(i);
                String pattern = "[1234567890]+";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(line);
                foo=0;
                if (m.find( )) foo=Integer.parseInt(m.group());
                Extract a = new Extract(ExtractType.Section, lista.get(i), foo);
                AktualnyArtykuł.list.add(a);
                AktualnyPunkt=a;
                Akt=a;
            }
            else if(matcherPpkt.find()==true) //jesli jest artykułem
            {
                String line = lista.get(i);
                String pattern = "[1234567890]+";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(line);
                foo=0;
                if (m.find( )) foo=Integer.parseInt(m.group());
                Extract a = new Extract(ExtractType.Point, lista.get(i), foo);
                AktualnyPunkt.list.add(a);
                AktualnyPodpunkt=a;
                Akt=a;
            }
            else if (matcherLitery.find()==true)
            {
                String line = lista.get(i);
                String pattern = "\\w\\)";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(line);
                char foo2='a';
                if (m.find( )) foo2=m.group().charAt(0);
                Extract a = new Extract(ExtractType.Sign, lista.get(i), (char) foo2);
                AktualnyPodpunkt.list.add(a);
                Akt=a;
            }
            else if (lista.get(i).length()>3 && lista.get(i).substring(0,3).equals("a. ")) //specyficznasytuacja dla uokiku
            {
                Extract a = new Extract(ExtractType.Section, lista.get(i), 1);
                Akt.list.add(a);
            }
            else
            {
                Extract a = new Extract(ExtractType.Root, lista.get(i), 0);
                Akt.list.add(a);
            }
        }
        return root;
    }





}
