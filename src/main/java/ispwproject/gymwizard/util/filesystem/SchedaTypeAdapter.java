package ispwproject.gymwizard.util.filesystem;

import com.google.gson.*;
import ispwproject.gymwizard.model.Scheda;

import java.lang.reflect.Type;

public class SchedaTypeAdapter implements JsonSerializer<Scheda>, JsonDeserializer<Scheda> {

    private static final String FIELD_ID_CLIENTE = "idCliente";
    private static final String FIELD_NOME_SCHEDA= "nomeScheda";

    @Override
    public JsonElement serialize(Scheda src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("id", src.getId());
        obj.addProperty(FIELD_ID_CLIENTE, src.getIdCliente());
        obj.addProperty(FIELD_NOME_SCHEDA, src.getNomeScheda());
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
        if (obj.has(FIELD_ID_CLIENTE) && !obj.get(FIELD_ID_CLIENTE).isJsonNull()) {
            scheda.setIdCliente(obj.get(FIELD_ID_CLIENTE).getAsInt());
        }
        if (obj.has(FIELD_NOME_SCHEDA) && !obj.get(FIELD_NOME_SCHEDA).isJsonNull()) {
            scheda.setNomeScheda(obj.get(FIELD_NOME_SCHEDA).getAsString());
        }
        if (obj.has("tipo") && !obj.get("tipo").isJsonNull()) {
            scheda.setTipo(obj.get("tipo").getAsString());
        }

        return scheda;
    }
}
