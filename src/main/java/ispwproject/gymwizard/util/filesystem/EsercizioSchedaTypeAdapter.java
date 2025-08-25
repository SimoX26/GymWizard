package ispwproject.gymwizard.util.filesystem;

import com.google.gson.*;
import ispwproject.gymwizard.model.EsercizioScheda;

import java.lang.reflect.Type;

public class EsercizioSchedaTypeAdapter implements JsonSerializer<EsercizioScheda>, JsonDeserializer<EsercizioScheda> {

    @Override
    public JsonElement serialize(EsercizioScheda src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("id", src.getId());
        obj.addProperty("idScheda", src.getIdScheda());
        obj.addProperty("nomeEsercizio", src.getNomeEsercizio());
        obj.addProperty("serie", src.getSerie());
        obj.addProperty("ripetizioni", src.getRipetizioni());
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
        if (obj.has("idScheda") && !obj.get("idScheda").isJsonNull()) {
            esercizio.setIdScheda(obj.get("idScheda").getAsInt());
        }
        if (obj.has("nomeEsercizio") && !obj.get("nomeEsercizio").isJsonNull()) {
            esercizio.setNomeEsercizio(obj.get("nomeEsercizio").getAsString());
        }
        if (obj.has("serie") && !obj.get("serie").isJsonNull()) {
            esercizio.setSerie(obj.get("serie").getAsInt());
        }
        if (obj.has("ripetizioni") && !obj.get("ripetizioni").isJsonNull()) {
            esercizio.setRipetizioni(obj.get("ripetizioni").getAsInt());
        }
        if (obj.has("note") && !obj.get("note").isJsonNull()) {
            esercizio.setNote(obj.get("note").getAsString());
        }

        return esercizio;
    }
}
