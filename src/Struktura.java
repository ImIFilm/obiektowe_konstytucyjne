import java.util.List;

public class Struktura {
    List<String> lista;

    Struktura(List<String> lis)
    {
        lista=lis;
    }

    /*MyTreeNode RozbijNaStrukture()
    {
        MyTreeNode root=new MyTreeNode("root");
        //punkty
        Pattern patternPunktu = Pattern.compile("\\d+\\.");
        String string=new String();
        for (int i=0; i<lista.size(); i++)
        {
            string=lista.get(i);
            string=string.substring(0, 3);
            Matcher matcher = patternPunktu.matcher();
            if(matcher.find()==true)
            {
                MyTreeNode punkt = new MyTreeNode();
                root.addChild(punkt);
            }
        }}
        return root; */
    }

