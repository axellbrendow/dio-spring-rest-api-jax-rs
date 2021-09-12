package br.com.axellbrendow.diospringrestapijaxrs.config;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.axellbrendow.diospringrestapijaxrs.enums.Race;

@Configuration
public class Jackson {
    @Bean
    public ObjectMapper mapper() {
        var mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(raceModuleMapper());
        return mapper;
    }

    public SimpleModule raceModuleMapper() {
        var dateModule = new SimpleModule("JSONRaceModule", PackageVersion.VERSION);
        dateModule.addSerializer(Race.class, new RaceSerializer());
        dateModule.addDeserializer(Race.class, new RaceDeserializer());
        return dateModule;
    }

    class RaceSerializer extends StdSerializer<Race> {
        public RaceSerializer() {
            super(Race.class);
        }

        @Override
        public void serialize(
            Race value,
            JsonGenerator gen,
            SerializerProvider provider
        ) throws IOException {
            gen.writeString(value.getValue());
        }
    }

    class RaceDeserializer extends StdDeserializer<Race> {
        public RaceDeserializer() {
            super(Race.class);
        }

        @Override
        public Race deserialize(
            JsonParser p,
            DeserializationContext ctxt
        ) throws IOException, JsonProcessingException {
            return Race.of(p.getText());
        }
    }
}
