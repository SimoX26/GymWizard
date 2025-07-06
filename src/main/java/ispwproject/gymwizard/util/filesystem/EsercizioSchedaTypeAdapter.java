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
        esercizio.setId(obj.get("id").getAsInt());
        esercizio.setIdScheda(obj.get("idScheda").getAsInt());
        esercizio.setNomeEsercizio(obj.get("nomeEsercizio").getAsString());
        esercizio.setSerie(obj.get("serie").getAsInt());
        esercizio.setRipetizioni(obj.get("ripetizioni").getAsInt());
        esercizio.setNote(obj.get("note").getAsString());
        return esercizio;
    }
}
