import java.io.IOException;
import java.util.List;

public class ParserKonstytucji extends Parser {
    ParserKonstytucji(String co) {
        super(co);
    }

    public Extract WczytujeIPoprawia() throws IOException
    {
        return super.WczytujeIPoprawia();
    }

    @Override
    protected List<String> zrobPreprocessing(Tekst s, List<String> listaString) {
        return listaString;
    }
}
