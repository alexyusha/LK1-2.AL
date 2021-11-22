package сom.work.example.myPackage.dict.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import сom.work.example.myPackage.model.InsuredPerson;

import java.io.IOException;
import java.util.List;

public class InsuredPersonDeserializer extends JsonDeserializer<List<InsuredPerson>> {

    @Override
    public List<InsuredPerson> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException{
        return new ObjectMapper().convertValue(p.readValueAsTree(), new TypeReference<List<InsuredPerson>>() {});
    }
}
