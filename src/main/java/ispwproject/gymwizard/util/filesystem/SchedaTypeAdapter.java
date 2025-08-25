package ispwproject.gymwizard.util.filesystem;

import com.google.gson.*;
import ispwproject.gymwizard.model.Scheda;

import java.lang.reflect.Type;

public class SchedaTypeAdapter implements JsonSerializer<Scheda>, JsonDeserializer<Scheda> {

    @Override
    public JsonElement serialize(Scheda src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("id", src.getId());
        obj.addProperty("idCliente", src.getIdCliente());
        obj.addProperty("nomeScheda", src.getNomeScheda());
        obj.addProperty("tipo", src.getTipo());
        return obj;
    }

    @Override
    public Scheda deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        Scheda scheda = new Scheda();

        if (obj.has("id") && !obj.get("id").isJsonNull()) {
            scheda.setId(obj.get("id").getAsInt());
        }
        if (obj.has("idCliente") && !obj.get("idCliente").isJsonNull()) {
            scheda.setIdCliente(obj.get("idCliente").getAsInt());
        }
        if (obj.has("nomeScheda") && !obj.get("nomeScheda").isJsonNull()) {
            scheda.setNomeScheda(obj.get("nomeScheda").getAsString());
        }
        if (obj.has("tipo") && !obj.get("tipo").isJsonNull()) {
            scheda.setTipo(obj.get("tipo").getAsString());
        }

        return scheda;
    }
}
