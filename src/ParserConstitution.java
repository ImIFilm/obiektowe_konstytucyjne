import java.io.IOException;
import java.util.List;

public class ParserConstitution extends Parser {
    ParserConstitution(String co) {
        super(co);
    }

    public Extract CreateAndImprove() throws IOException
    {
        return super.CreateAndImprove();
    }

    @Override
    protected List<String> doPreprocessing(Tekst s, List<String> listaString) {
        return listaString;
    }
}
