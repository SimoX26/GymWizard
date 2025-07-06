package ispwproject.gymwizard.util.filesystem;

import com.google.gson.*;
import ispwproject.gymwizard.model.Scheda;

import java.lang.reflect.Type;
import java.sql.Timestamp;

public class SchedaTypeAdapter implements JsonSerializer<Scheda>, JsonDeserializer<Scheda> {

    @Override
    public JsonElement serialize(Scheda src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("id", src.getId());
        obj.addProperty("idCliente", src.getIdCliente());
        obj.addProperty("nomeScheda", src.getNomeScheda());
        obj.addProperty("dataCreazione", src.getDataCreazione() != null ? src.getDataCreazione().getTime() : null);
        return obj;
    }

    @Override
    public Scheda deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        Scheda scheda = new Scheda();
        scheda.setId(obj.get("id").getAsInt());
        scheda.setIdCliente(obj.get("idCliente").getAsInt());
        scheda.setNomeScheda(obj.get("nomeScheda").getAsString());
        if (obj.has("dataCreazione") && !obj.get("dataCreazione").isJsonNull()) {
            scheda.setDataCreazione(new Timestamp(obj.get("dataCreazione").getAsLong()));
        }
        return scheda;
    }
}
