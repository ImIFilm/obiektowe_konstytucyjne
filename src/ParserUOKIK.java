import java.io.IOException;
import java.util.List;

public class ParserUOKIK extends Parser {

    ParserUOKIK(String co) {
        super(co);
    }

    public Extract WczytujeIPoprawia() throws IOException
    {
        return super.WczytujeIPoprawia();
    }

    @Override
    protected List<String> zrobPreprocessing(Tekst źródło, List<String> stringi) {
            return źródło.uporczyweArtykuły(); //jesli uokik, to trzeba poradzic sobie z nowym problemem

    }

}
