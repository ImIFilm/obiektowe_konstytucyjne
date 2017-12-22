import java.io.IOException;
import java.util.List;

public class ParserUOKIK extends Parser {

    ParserUOKIK(String co) {
        super(co);
    }

    public Extract CreateAndImprove() throws IOException
    {
        return super.CreateAndImprove();
    }

    @Override
    protected List<String> doPreprocessing(Tekst źródło, List<String> stringi) {
            return źródło.uporczyweArtykuły(); //jesli uokik, to trzeba poradzic sobie z nowym problemem

    }

}
