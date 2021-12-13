package сom.work.example.myPackage.model.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CalendarDeserializer extends JsonDeserializer<Calendar> {

    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public Calendar deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(p.getText()));
            return calendar;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
