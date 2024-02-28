package uz.pdp.eufloria.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateJsonSerializer extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(LocalDate value, JsonGenerator gen,
                          SerializerProvider serializers) throws IOException {
        gen.writeString(value.getMonthValue() + "/" + value.getYear());
    }
}
