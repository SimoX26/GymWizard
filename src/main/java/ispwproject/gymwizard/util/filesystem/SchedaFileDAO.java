package ispwproject.gymwizard.util.filesystem;

import com.google.gson.reflect.TypeToken;
import ispwproject.gymwizard.model.Scheda;

import java.lang.reflect.Type;
import java.util.List;

public class SchedaFileDAO {

    private static final String FILE_NAME = "schede.json";
    private static final Type LIST_TYPE = new TypeToken<List<Scheda>>() {}.getType();

    public SchedaFileDAO() {
        //Costruttore pubblico
    }

    public void insertScheda(Scheda scheda) {
        int idCliente = scheda.getIdCliente();
        List<Scheda> schede = FileSystemManager.loadListFromFile(idCliente, FILE_NAME, LIST_TYPE);

        scheda.setId(generaNuovoId(schede));

        schede.add(scheda);
        FileSystemManager.saveListToFile(schede, idCliente, FILE_NAME);
    }

    private int generaNuovoId(List<Scheda> schede) {
        return schede.stream()
                .mapToInt(Scheda::getId)
                .max()
                .orElse(0) + 1;
    }
}
