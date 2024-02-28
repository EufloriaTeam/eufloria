package uz.pdp.eufloria;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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

import java.io.FileInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class EufloriaApplication {
    @Value("${gsc.serviceAccountKeyPath}")
    private String serviceAccountKeyPath;

    public static void main(String[] args) {
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
            builder.simpleDateFormat("yyyy-MM");
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM")));
        };
    }
}
