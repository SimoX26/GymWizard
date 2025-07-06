package ispwproject.gymwizard.util.filesystem;

import com.google.gson.reflect.TypeToken;
import ispwproject.gymwizard.model.Scheda;
import ispwproject.gymwizard.util.exception.DAOException;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SchedaFileDAO {

    private static final String FILE_NAME = "schede.json";
    private static final Type LIST_TYPE = new TypeToken<List<Scheda>>() {}.getType();
    private static SchedaFileDAO instance;

    private SchedaFileDAO() {}

    public static synchronized SchedaFileDAO getInstance() {
        if (instance == null) {
            instance = new SchedaFileDAO();
        }
        return instance;
    }

    public void insertScheda(Scheda scheda){
        int idCliente = scheda.getIdCliente();
        List<Scheda> schede = FileSystemManager.loadListFromFile(idCliente, FILE_NAME, LIST_TYPE);

        // Genera un nuovo ID
        scheda.setId(generaNuovoId(schede));

        // Se non presente, imposta la data di creazione
        if (scheda.getDataCreazione() == null) {
            scheda.setDataCreazione(new Timestamp(System.currentTimeMillis()));
        }

        schede.add(scheda);
        FileSystemManager.saveListToFile(schede, idCliente, FILE_NAME);
    }

    public List<Scheda> getSchedeByUtente(int idCliente) {
        // ✅ Cast sicuro a List<Scheda>
        List<?> rawList = FileSystemManager.loadListFromFile(idCliente, FILE_NAME, LIST_TYPE);
        List<Scheda> schede = new ArrayList<>();

        for (Object o : rawList) {
            if (o instanceof Scheda) {
                schede.add((Scheda) o);
            }
        }

        return schede;
    }

    private int generaNuovoId(List<Scheda> schede) {
        return schede.stream()
                .mapToInt(Scheda::getId)
                .max()
                .orElse(0) + 1;
    }
}
