package ispwproject.gymwizard.util.filesystem;

import com.google.gson.reflect.TypeToken;
import ispwproject.gymwizard.model.EsercizioScheda;

import java.lang.reflect.Type;
import java.util.List;

public class EsercizioSchedaFileDAO {

    private static final String FILE_NAME = "esercizi_scheda.json";
    private static final Type LIST_TYPE = new TypeToken<List<EsercizioScheda>>() {}.getType();

    public EsercizioSchedaFileDAO() {
        //Costruttore pubblico
    }

    public void insertEsercizio(int idCliente, EsercizioScheda esercizio) {
        List<EsercizioScheda> lista = FileSystemManager.loadListFromFile(idCliente, FILE_NAME, LIST_TYPE);
        esercizio.setId(generaNuovoId(lista));
        lista.add(esercizio);
        FileSystemManager.saveListToFile(lista, idCliente, FILE_NAME);
    }

    private int generaNuovoId(List<EsercizioScheda> esercizi) {
        return esercizi.stream()
                .mapToInt(EsercizioScheda::getId)
                .max()
                .orElse(0) + 1;
    }
}
