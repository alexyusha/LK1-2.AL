import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;


public class ClientDeserializer extends JsonDeserializer<Client> {

    @Override
    public Client deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            JsonNode node = p.readValueAsTree();
            if (node.isObject()){
                String type = node.get("typeClient").asText();
                String name = node.get("name").asText();
                String address = node.get("address").asText();
                return new Client(type.equals("INDIVIDUAL") ? TypeClient.INDIVIDUAL : TypeClient.ENTITY, name, address);
            }
            else{
                return null;
            }
        }catch (Exception e){
            throw new IOException(e);
        }

    }
}
