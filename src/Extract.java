import java.util.LinkedList;
import java.util.List;

public class Extract {
    ExtractType type;
    String content;
    int number = 0;
    List<Extract> list = new LinkedList<>();
    char sign = '-';

    Extract() {
        type = ExtractType.Root;
    }

    Extract(ExtractType t, String s, int o) {
        type = t;
        content = s;
        number = o;
    }

    Extract(ExtractType t, String s, List<Extract> l, int o) {
        type = t;
        content = s;
        list = l;
        number = o;
    }

    Extract(ExtractType t, String s, char z) {
        type = t;
        content = s;
        sign = z;
    }

    Extract(ExtractType t, String s, int u, char z) {
        type = t;
        content = s;
        sign = z;
        number = u;
    }

    Extract(ExtractType t, String s, List<Extract> l, int o, char z) {
        type = t;
        content = s;
        list = l;
        number = o;
        sign = z;
    }


    @Override
    public String toString() {
        return content;
    }

    public void DeepPreview(Extract fra) {
        System.out.println("| number: " + fra.number + fra.sign + " -> " + fra.type + "|--- " + fra.content + " ");
        for (int i = 0; i < fra.list.size(); i++) {
            if (fra.list.get(i) != null) DeepPreview(fra.list.get(i));
        }
    }

    public void WypiszArtykuły(Extract fra) {
        if (fra.type == ExtractType.Article) {
            System.out.println("|" + fra.number + fra.sign + "|--- " + fra.content + " ");
        }
        for (int i = 0; i < fra.list.size(); i++) {
            if (fra.list.get(i) != null) WypiszArtykuły(fra.list.get(i));
        }
    }

    public void ShowTableOfContent(Extract fra) {
        if (
                (fra.type == ExtractType.Rozdział || fra.type == ExtractType.Root) &&
                        (fra.content != null) &&
                        (fra.content.length() > 3) &&
                        !(fra.content.substring(0, 4).matches("\\p{Lu}"))
                ) {
            if (fra.type == ExtractType.Root) System.out.println("   " + fra.content + " ");
            else System.out.println(" " + fra.content + " ");
        }
        for (int i = 0; i < fra.list.size(); i++) {
            if (fra.list.get(i) != null) ShowTableOfContent(fra.list.get(i));
        }
    }

/*
    public void WypiszArtykułKonstytucji(int a, Extract fra) {
        for (int i = 0; i < fra.list.size(); i++) {
            for (int j = 0; j < fra.list.get(i).list.size(); j++) {
                if (fra.list.get(i).list.get(j).type == ExtractType.Article && fra.list.get(i).list.get(j).number == a) {
                    DeepPreview(fra.list.get(i).list.get(j));
                }
            }
        }
    }
*/

    public void ShowSpecificSection(int a, int b, Extract fra, ExtractType typek)
    {
        if (fra.type ==typek && fra.number ==a) {
            if (fra.type == ExtractType.Section && fra.number ==b) System.out.println(fra);
            else {
                for (int i = 0; i < fra.list.size(); i++) {
                    if (fra.list.get(i) != null) ShowSpecificSection(a, b, fra.list.get(i), ExtractType.Section);
                }
            }
        }
        else {
            for (int i = 0; i < fra.list.size(); i++) {
                if (fra.list.get(i) != null) ShowSpecificSection(a, b, fra.list.get(i), ExtractType.Article);
            }
        }
    }

    public void ShowSpecificArticle(int a, Extract fra)
    {
        if (fra.type == ExtractType.Article && fra.number ==a) DeepPreview(fra);
        else {
            for (int i = 0; i < fra.list.size(); i++) {
                if (fra.list.get(i) != null) ShowSpecificArticle(a, fra.list.get(i));
            }
        }
    }

    public void ShowSpecificArticle(int a, Extract fra, char q)
    {
        if (fra.type == ExtractType.Article && fra.number ==a && fra.sign ==q) DeepPreview(fra);
        else {
            for (int i = 0; i < fra.list.size(); i++) {
                if (fra.list.get(i) != null) ShowSpecificArticle(a, fra.list.get(i), q);
            }
        }
    }

    /*
    public void ShowSpecificSection (int a, int b, Extract fra)
    {
        if (fra.type==ExtractType.Article && fra.number==a)
            if(fra.UzyskajTyp(b, fra)!=null)
                DeepPreview(fra.UzyskajTyp(b, fra));
        else {
            for (int i = 0; i < fra.list.size(); i++) {
                if (fra.list.get(i) != null) ShowSpecificSection(a, b, fra.list.get(i));
            }
        }
    }
*/
    public Extract UzyskajTyp (int a, Extract fra)
    {
        for (int i = 0; i < fra.list.size(); i++) {
            if (fra.list.get(i).number ==a) return fra.list.get(i);
            }
        return null;
    }



    public void WypiszCoWeSciezce(int art, int pkt, Extract fra) {
        for (int i = 0; i < fra.list.size(); i++) {
            for (int j = 0; j < fra.list.get(i).list.size(); j++) {
                //POZIOM ARTYKUŁU
                if (fra.list.get(i).list.get(j).type == ExtractType.Article && fra.list.get(i).list.get(j).number > art - 1 &&
                        fra.list.get(i).list.get(j).number < art + 1) {
                    for (int k = 0; k < fra.list.get(i).list.get(j).list.size(); k++) {
                        //POZIOM PUNKTU
                        if (fra.list.get(i).list.get(j).list.get(k).type == ExtractType.Section &&
                                fra.list.get(i).list.get(j).list.get(k).number == pkt)
                            DeepPreview(fra.list.get(i).list.get(j).list.get(k));
                    }
                }
            }
        }
    }

    public void WypiszCoWeSciezce(int art, int pkt, int ppkt, Extract fra) {
        for (int i = 0; i < fra.list.size(); i++) {
            for (int j = 0; j < fra.list.get(i).list.size(); j++) {
                //POZIOM ARTYKUŁU
                if (fra.list.get(i).list.get(j).type == ExtractType.Article && fra.list.get(i).list.get(j).number > art - 1 &&
                        fra.list.get(i).list.get(j).number < art + 1) {
                    for (int k = 0; k < fra.list.get(i).list.get(j).list.size(); k++) {
                        //POZIOM PUNKTU
                        if (fra.list.get(i).list.get(j).list.get(k).type == ExtractType.Section &&
                                fra.list.get(i).list.get(j).list.get(k).number == pkt)
                        {
                            for (int l = 0; l < fra.list.get(i).list.get(j).list.get(k).list.size(); l++) {
                                //POZIOM PODPUNKTU
                                if (fra.list.get(i).list.get(j).list.get(k).list.get(l).type == ExtractType.Point &&
                                        fra.list.get(i).list.get(j).list.get(k).list.get(l).number == ppkt)
                                    DeepPreview(fra.list.get(i).list.get(j).list.get(k).list.get(l));
                            }
                        }
                    }
                }
            }
        }

    }

    public void WypiszCoWeSciezce(int art, int pkt, int ppkt, char litera, Extract fra) {
        for (int i = 0; i < fra.list.size(); i++) {
            for (int j = 0; j < fra.list.get(i).list.size(); j++) {
                //POZIOM ARTYKUŁU
                if (fra.list.get(i).list.get(j).type == ExtractType.Article && fra.list.get(i).list.get(j).number > art - 1 &&
                        fra.list.get(i).list.get(j).number < art + 1) {
                    for (int k = 0; k < fra.list.get(i).list.get(j).list.size(); k++) {
                        //POZIOM PUNKTU
                        if (fra.list.get(i).list.get(j).list.get(k).type == ExtractType.Section &&
                                fra.list.get(i).list.get(j).list.get(k).number == pkt)

                        {
                            for (int l = 0; l < fra.list.get(i).list.get(j).list.get(k).list.size(); l++) {
                                //POZIOM PODPUNKTU
                                if (fra.list.get(i).list.get(j).list.get(k).list.get(l).type == ExtractType.Point &&
                                        fra.list.get(i).list.get(j).list.get(k).list.get(l).number == ppkt) {
                                    for (int m = 0; m < fra.list.get(i).list.get(j).list.get(k).list.get(l).list.size(); m++) {
                                        //POZIOM PODPUNKTU
                                        if (fra.list.get(i).list.get(j).list.get(k).list.get(l).list.get(m).type == ExtractType.Sign &&
                                                fra.list.get(i).list.get(j).list.get(k).list.get(l).list.get(m).sign == litera)
                                            DeepPreview(fra.list.get(i).list.get(j).list.get(k).list.get(l).list.get(m));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void ZAKRESWypiszArtykuły(int a, int b, Extract fra)
    {
        if (fra.type == ExtractType.Article && fra.number >=a && fra.number<=b) DeepPreview(fra);

            for (int i = 0; i < fra.list.size(); i++) {
                if (fra.list.get(i) != null) ZAKRESWypiszArtykuły(a, b, fra.list.get(i));
            }

    }

    /*public void WypiszArtykuły2(int a, int b, Extract fra) {
        for (int i = 0; i < fra.list.size(); i++) {
            for (int j = 0; j < fra.list.get(i).list.size(); j++) {
                if (fra.list.get(i).list.get(j).type == ExtractType.Article && fra.list.get(i).list.get(j).number > a - 1 &&
                        fra.list.get(i).list.get(j).number < b + 1) {
                    DeepPreview(fra.list.get(i).list.get(j));
                }
            }
        }
    }*/

    public void WypiszArtykuły(int a, int b, Extract fra, char znak) {
        System.out.print(znak);
        for (int i = 0; i < fra.list.size(); i++) {
            for (int j = 0; j < fra.list.get(i).list.size(); j++) {
                if ((fra.list.get(i).list.get(j).type == ExtractType.Article) && (fra.list.get(i).list.get(j).number > (a - 1)) &&
                        (fra.list.get(i).list.get(j).number < (b + 1)) &&
                        (fra.list.get(i).list.get(j).sign == znak)) {
                    DeepPreview(fra.list.get(i).list.get(j));
                }
            }
        }
    }

    public void WypiszRozdziałKonstytucji(int a, Extract fra) {
        for (int j = 0; j < fra.list.size(); j++) {
            if (fra.list.get(j).type == ExtractType.Rozdział && fra.list.get(j).number == a) {
                DeepPreview(fra.list.get(j));
            }
        }
    }

    public void WypiszRozdziałKonstytucjiZZakresu(int a, int b, Extract fra) {
        for (int j = 0; j < fra.list.size(); j++) {
            if (fra.list.get(j).type == ExtractType.Rozdział && fra.list.get(j).number >= a && fra.list.get(j).number <= b) {
                DeepPreview(fra.list.get(j));
            }
        }
    }
}
