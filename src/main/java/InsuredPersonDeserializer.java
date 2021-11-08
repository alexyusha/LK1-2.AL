import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Set;

public class InsuredPersonDeserializer extends JsonDeserializer<Set<InsuredPerson>> {

    @Override
    public Set<InsuredPerson> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException{
        return new ObjectMapper().convertValue(p.readValueAsTree(), new TypeReference<Set<InsuredPerson>>() {});
    }
}
