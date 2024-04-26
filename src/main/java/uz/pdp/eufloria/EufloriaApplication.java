package uz.pdp.eufloria;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableMethodSecurity
@EnableWebSecurity
@EnableWebMvc
public class EufloriaApplication {
    @Value("${gsc.serviceAccountKeyPath}")
    private String serviceAccountKeyPath;

    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        SpringApplication.run(EufloriaApplication.class, args);
    }

    @Bean
    public Storage storage() throws IOException {
        return StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream(serviceAccountKeyPath)))
                .build().getService();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.serializers(new LocalTimeSerializer(DateTimeFormatter.ISO_LOCAL_TIME));
            builder.deserializers(new LocalTimeDeserializer(DateTimeFormatter.ISO_LOCAL_TIME));
            builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        };
    }
}
