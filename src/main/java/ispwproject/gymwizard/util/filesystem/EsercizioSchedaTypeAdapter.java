package ispwproject.gymwizard.util.filesystem;

import com.google.gson.*;
import ispwproject.gymwizard.model.EsercizioScheda;

import java.lang.reflect.Type;

public class EsercizioSchedaTypeAdapter implements JsonSerializer<EsercizioScheda>, JsonDeserializer<EsercizioScheda> {

    private static final String FIELD_ID_SCHEDA = "idScheda";
    private static final String FIELD_NOME_ESERCIZIO = "nomeEsercizio";
    private static final String FIELD_SERIE = "serie";
    private static final String FIELD_RIPETIZIONI = "ripetizioni";


    @Override
    public JsonElement serialize(EsercizioScheda src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("id", src.getId());
        obj.addProperty(FIELD_ID_SCHEDA, src.getIdScheda());
        obj.addProperty(FIELD_NOME_ESERCIZIO, src.getNomeEsercizio());
        obj.addProperty(FIELD_SERIE, src.getSerie());
        obj.addProperty(FIELD_RIPETIZIONI, src.getRipetizioni());
        obj.addProperty("note", src.getNote());
        return obj;
    }

    @Override
    public EsercizioScheda deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        EsercizioScheda esercizio = new EsercizioScheda();

        // 🔹 Controlli per evitare NPE su vecchi JSON
        if (obj.has("id") && !obj.get("id").isJsonNull()) {
            esercizio.setId(obj.get("id").getAsInt());
        }
        if (obj.has(FIELD_ID_SCHEDA) && !obj.get(FIELD_ID_SCHEDA).isJsonNull()) {
            esercizio.setIdScheda(obj.get(FIELD_ID_SCHEDA).getAsInt());
        }
        if (obj.has(FIELD_NOME_ESERCIZIO) && !obj.get(FIELD_NOME_ESERCIZIO).isJsonNull()) {
            esercizio.setNomeEsercizio(obj.get(FIELD_NOME_ESERCIZIO).getAsString());
        }
        if (obj.has(FIELD_SERIE) && !obj.get(FIELD_SERIE).isJsonNull()) {
            esercizio.setSerie(obj.get(FIELD_SERIE).getAsInt());
        }
        if (obj.has(FIELD_RIPETIZIONI) && !obj.get(FIELD_RIPETIZIONI).isJsonNull()) {
            esercizio.setRipetizioni(obj.get(FIELD_RIPETIZIONI).getAsInt());
        }
        if (obj.has("note") && !obj.get("note").isJsonNull()) {
            esercizio.setNote(obj.get("note").getAsString());
        }

        return esercizio;
    }
}
