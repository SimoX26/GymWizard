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
        return obj;
    }

    @Override
    public Scheda deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        Scheda scheda = new Scheda();
        scheda.setId(obj.get("id").getAsInt());
        scheda.setIdCliente(obj.get("idCliente").getAsInt());
        scheda.setNomeScheda(obj.get("nomeScheda").getAsString());
        return scheda;
    }
}
