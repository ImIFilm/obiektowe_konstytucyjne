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
        //try{System.out.println("NUMER:" + fra.number + " ZNAK:"+fra.sign + " TYP:" + fra.type + "||| " + fra.content);}
        //catch (NullPointerException e) {}
        System.out.println("NUMER:" + fra.number + " ZNAK:"+fra.sign + " TYP:" + fra.type + "||| " + fra.content);
        for (int i = 0; i < fra.list.size(); i++) {
            if (fra.list.get(i) != null) DeepPreview(fra.list.get(i));
        }
    }

    public void ShowTableOfContent(Extract fra) {
        if (
                (fra.type == ExtractType.Chapter || fra.type == ExtractType.Root) &&
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


    public void ShowArticlesFromTo(int a, int b, Extract fra)
    {
        if (fra.type == ExtractType.Article && fra.number >=a && fra.number<=b) DeepPreview(fra);
        for (int i = 0; i < fra.list.size(); i++) {
            if (fra.list.get(i) != null) ShowArticlesFromTo(a, b, fra.list.get(i));
        }
    }

    public Extract ChildOfNumber(int a){
        for (int i=0; i<list.size(); i++)
        {
            if(list!=null && list.get(i)!=null && list.get(i).number==a) return list.get(i);
        }
        return null;
    }

    public Extract ChildOfSign(char a){
        for (int i=0; i<list.size(); i++)
        {
            if(list!=null && list.get(i)!=null && list.get(i).sign==a) return list.get(i);
        }
        return null;
    }


    public void ShowSpecificSection(int a, int b, Extract fra)
    {
        //try {DeepPreview(fra.ChildOfNumber( b));}
        //catch (NullPointerException e) {}
        if (fra.type == ExtractType.Article && fra.number ==a) DeepPreview(fra.ChildOfNumber( b));
        else {
            for (int i = 0; i < fra.list.size(); i++) {
                if (fra.list.get(i) != null) ShowSpecificSection(a, b, fra.list.get(i));
            }
        }
    }

    public void ShowSpecificPoint(int a, int b, int c, Extract fra)
    {
        //try {DeepPreview(fra.ChildOfNumber(b).ChildOfNumber(c));}
        //catch (NullPointerException e) {}
        if (fra.type == ExtractType.Article && fra.number ==a) DeepPreview(fra.ChildOfNumber(b).ChildOfNumber(c));
        else {
            for (int i = 0; i < fra.list.size(); i++) {
                if (fra.list.get(i) != null) ShowSpecificPoint(a, b, c, fra.list.get(i));
            }
        }
    }

    public void ShowSpecificSign(int a, int b, int c, char d, Extract fra)
    {
        //try {DeepPreview(fra.ChildOfNumber(b).ChildOfNumber(c).ChildOfSign(d));}
        //catch (NullPointerException e) {}
        if (fra.type == ExtractType.Article && fra.number ==a)
            DeepPreview(fra.ChildOfNumber(b).ChildOfNumber(c).ChildOfSign(d));
        else {
            for (int i = 0; i < fra.list.size(); i++) {
                if (fra.list.get(i) != null) ShowSpecificSign(a, b, c, d, fra.list.get(i));
            }
        }
    }

    public void WypiszRozdziałKonstytucji(int a, Extract fra) {
        for (int j = 0; j < fra.list.size(); j++) {
            if (fra.list.get(j).type == ExtractType.Chapter && fra.list.get(j).number == a) {
                DeepPreview(fra.list.get(j));
            }
        }
    }

    public void WypiszRozdziałKonstytucjiZZakresu(int a, int b, Extract fra) {
        for (int j = 0; j < fra.list.size(); j++) {
            if (fra.list.get(j).type == ExtractType.Chapter && fra.list.get(j).number >= a && fra.list.get(j).number <= b) {
                DeepPreview(fra.list.get(j));
            }
        }
    }

    public void ShowSpecificChapter(int a, Extract fra)
    {
        if (fra.type == ExtractType.Chapter && fra.number ==a) fra.DeepPreview(fra);
        else {
            for (int i = 0; i < fra.list.size(); i++) {
                if (fra.list.get(i) != null) fra.ShowSpecificChapter(a, fra.list.get(i));
            }
        }
    }

    public void ShowSpecificChapterFromUnit(int a, int unit, Extract fra)
    {
        if (fra.type == ExtractType.Unit && fra.number ==unit) fra.DeepPreview(fra.ChildOfNumber(a));
        else {
            for (int i = 0; i < fra.list.size(); i++) {
                if (fra.list.get(i) != null) fra.ShowSpecificChapterFromUnit(a, unit, fra.list.get(i));
            }
        }
    }
}