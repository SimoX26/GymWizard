package ispwproject.gymwizard.util.filesystem;

import com.google.gson.reflect.TypeToken;
import ispwproject.gymwizard.model.EsercizioScheda;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.singleton.SessionManager;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class EsercizioSchedaFileDAO {

    private static final String FILE_NAME = "esercizi_scheda.json";
    private static final Type LIST_TYPE = new TypeToken<List<EsercizioScheda>>() {}.getType();
    private static EsercizioSchedaFileDAO instance;

    private EsercizioSchedaFileDAO() {}

    public static synchronized EsercizioSchedaFileDAO getInstance() {
        if (instance == null) {
            instance = new EsercizioSchedaFileDAO();
        }
        return instance;
    }

    public void insertEsercizio(EsercizioScheda esercizio, int idCliente) {

        // Per ricavare il cliente serve una mappatura scheda → cliente
        // Supponiamo che tu abbia già messo l’idCliente nel SessionManager → lo recuperiamo così:
        Utente cliente = (Utente) SessionManager.getInstance().getAttributo("clienteSelezionato");
        int idUtente = cliente.getId();


        List<EsercizioScheda> lista = FileSystemManager.loadListFromFile(idUtente, FILE_NAME, LIST_TYPE);
        esercizio.setId(generaNuovoId(lista));
        lista.add(esercizio);
        FileSystemManager.saveListToFile(lista, idUtente, FILE_NAME);
    }

    public List<EsercizioScheda> getEserciziByScheda(int idScheda, int idCliente) {
        List<EsercizioScheda> lista = FileSystemManager.loadListFromFile(idCliente, FILE_NAME, LIST_TYPE);
        return lista.stream()
                .filter(e -> e.getIdScheda() == idScheda)
                .collect(Collectors.toList());
    }

    private int generaNuovoId(List<EsercizioScheda> esercizi) {
        return esercizi.stream()
                .mapToInt(EsercizioScheda::getId)
                .max()
                .orElse(0) + 1;
    }
}
