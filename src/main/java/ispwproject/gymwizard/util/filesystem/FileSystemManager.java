package ispwproject.gymwizard.util.filesystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import ispwproject.gymwizard.model.EsercizioScheda;
import ispwproject.gymwizard.model.Scheda;
import ispwproject.gymwizard.util.logger.AppLogger;

import java.io.*;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FileSystemManager {

    private static final String BASE_PATH = "data/clienti/";

    private FileSystemManager(){
        //Costruttore privato
    }

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Timestamp.class, new TimestampTypeAdapter())
            .registerTypeAdapter(Scheda.class, new SchedaTypeAdapter())
            .registerTypeAdapter(EsercizioScheda.class, new EsercizioSchedaTypeAdapter())
            .setPrettyPrinting()
            .create();

    // Salva la lista in: data/clienti/<idCliente>/<fileName>
    public static <T> void saveListToFile(List<T> list, int idCliente, String fileName) {
        String filePath = BASE_PATH + idCliente + fileName;
        File file = new File(filePath);
        File dir = file.getParentFile();

        if (dir != null && !dir.exists()) {
            dir.mkdirs();
        }

        try (Writer writer = new FileWriter(file)) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            AppLogger.logError("⚠️ Errore salvataggio su FileSystem: " + e.getMessage());
        }
    }

    // Carica lista da: data/clienti/<idCliente>/<fileName>
    public static <T> List<T> loadListFromFile(int idCliente, String fileName, Type type) {
        String filePath = BASE_PATH + idCliente + fileName;
        File file = new File(filePath);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            return gson.fromJson(reader, type);
        } catch (JsonSyntaxException e) {
            AppLogger.logError("❌ JSON malformato nel file: " + filePath + " → backup e sovrascrittura con lista vuota");

            File backup = new File(filePath + ".bak");
            if (file.renameTo(backup)) {
                AppLogger.logError("📁 Backup creato: " + backup.getPath());
            }

            saveListToFile(new ArrayList<T>(), idCliente, fileName);
            return new ArrayList<>();
        } catch (IOException e) {
            AppLogger.logError("⚠️ Errore lettura FileSystem: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

